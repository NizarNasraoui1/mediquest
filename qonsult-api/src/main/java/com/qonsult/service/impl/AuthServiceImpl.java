package com.qonsult.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.qonsult.config.tenant_config.TenantContext;
import com.qonsult.config.tenant_config.TenantDataSource;
import com.qonsult.dto.*;
import com.qonsult.entity.*;
import com.qonsult.exception.EntityException;
import com.qonsult.mapper.UserMapper;
import com.qonsult.repository.ValidationTokenRepository;
import com.qonsult.repository.PermissionRepository;
import com.qonsult.repository.RoleRepository;
import com.qonsult.repository.UserRepository;
import com.qonsult.service.*;
import com.qonsult.util.ForgotPasswordEmailTemplate;
import com.qonsult.util.ValidateEmailTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final ApplicationContext context;
    private final UserRepository userRepository;
    private final ValidationTokenRepository validationTokenRepository;
    private final EmailSenderService emailSenderService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final CenterService centerService;
    private final SchemaService schemaService;
    private final UserService userService;
    @Value("${reset-password-url}")
    private String resetPasswordUrl;
    @Value("${mail-validation-url}")
    private String mailValidationUrl;


    @Override
    public Mono<AuthResponseDTO> authenticate(AuthRequest authRequest) {
        return Mono.fromCallable(() -> userService.loadUserByUsername(authRequest.getUsername(),authRequest.getPassword()))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(user -> {
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                    TenantDataSource tenantDataSource = context.getBean(TenantDataSource.class);
                    String schema = tenantDataSource.getUsernameSchemaMap().get(authRequest.getUsername());

                    String accessToken = generateToken(user.getUsername(),user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()),algorithm,false);
                    String refreshToken = generateToken(user.getUsername(),user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()),algorithm,true);

                    AuthResponseDTO authResponseDTO = new AuthResponseDTO();
                    authResponseDTO.setAccessToken(accessToken);
                    authResponseDTO.setRefreshToken(refreshToken);
                    authResponseDTO.setTenant(schema);
                    return Mono.just(authResponseDTO);
                });
    }

    public String generateToken(String username,List<String>authorities,Algorithm algorithm,boolean refreshToken){
        int duration = 60 * 60 * 1000;
        if(refreshToken){
            duration = duration * 2;
        }
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + duration))
                .withClaim("permissions",authorities)
                .sign(algorithm);
    }

    public Mono<AuthResponseDTO> getAccessTokenFromRefreshToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        String accessToken = generateToken(username,decodedJWT.getClaim("permissions").asList(String.class),algorithm,false);
        String refreshToken = generateToken(username,decodedJWT.getClaim("permissions").asList(String.class),algorithm,true);

        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setAccessToken(accessToken);
        authResponseDTO.setRefreshToken(refreshToken);
        return Mono.just(authResponseDTO);
    }

    @Override
    public Mono<Void> processForgotPassword(String userEmail) {
            return Mono.justOrEmpty(userRepository.findByEmail(userEmail))
                    .switchIfEmpty(Mono.error(new EntityException("not found")))
                    .flatMap(user->{
                                ValidationToken token = new ValidationToken();
                                String emailToken = UUID.randomUUID().toString();
                                token.setToken(emailToken);
                                token.setUser(user);
                                token.setExpiryDate(Instant.ofEpochSecond(3600));
                                validationTokenRepository.save(token);
                                return Mono.justOrEmpty(emailToken);
                            }
                    )
                            .flatMap(emailToken->{
                                String emailBody = ForgotPasswordEmailTemplate.generateForgotMailTemplate(resetPasswordUrl,emailToken);
                                emailSenderService.sendEmail("nizar.al.nasraoui@gmail.com",
                                        "Réinitialisation de mot de passe",
                                        emailBody);
                                return Mono.empty();
                            });
    }

    @Override
    public Mono<Void>changePassword(ResetPasswordRequestDTO resetPasswordRequestDTO){
        return Mono.justOrEmpty(validationTokenRepository.findByToken(resetPasswordRequestDTO.getResetPasswordToken()))
                .switchIfEmpty(Mono.error(new EntityException("not found")))
                .flatMap((token)->{
                    User user = token.getUser();
                    user.setPassword(passwordEncoder.encode(resetPasswordRequestDTO.getPassword()));
                    userRepository.save(user);
                    return Mono.empty();
                });
    }

    @Override
    public Mono<Void> validateMail(String validationToken){
        return Mono.justOrEmpty(validationTokenRepository.findByToken(validationToken))
                .switchIfEmpty(Mono.error(new EntityException("not found")))
                .flatMap((token)->{
                    User user = token.getUser();
                    user.setEmailChecked(true);
                    userRepository.save(user);
                    return Mono.empty();
                });
    }


    @Override
    public Mono<SuccessResponseDTO> register(RegisterDTO registerDTO){
        UserDTO userDTO = registerDTO.getUser();
        CenterDTO centerDTO = registerDTO.getCenter();
        if(userRepository.findByUsername(userDTO.getUsername())!=null){
            return Mono.error(new EntityException("user already exists"));
        }
        String schemaName = "_"+ UUID.randomUUID().toString().substring(0,6);
        Center center = centerService.addCenter(centerDTO,schemaName).block();
        Role centerAdminRole = new Role();
        centerAdminRole.setName("CENTER_ADMIN");
        centerAdminRole.setCenter(center);
        roleRepository.save(centerAdminRole);
        List<Permission> permissions = permissionRepository.findAllByNameIn(Arrays.asList("READ_QUESTIONNAIRE","ADD_QUESTIONNAIRE"));
        centerAdminRole.setPermissions(permissions);
        User user = userMapper.toBo(userDTO);
        user.setRole(centerAdminRole);
        user.setPassword(passwordEncoder.encode(registerDTO.getUser().getPassword()));
        schemaService.addSchema(schemaName,userDTO.getUsername());
        userService.saveUser(user);
        emailVerification(user);
        SuccessResponseDTO successResponseDTO = new SuccessResponseDTO();
        successResponseDTO.setMessage("user added successfully");
        return Mono.justOrEmpty(successResponseDTO);
    }

    public void emailVerification(User user){
        ValidationToken validationToken = new ValidationToken();
        validationToken.setUser(user);
        String emailValidationToken = UUID.randomUUID().toString();
        validationToken.setToken(emailValidationToken);
        validationTokenRepository.save(validationToken);
        String emailBody = ValidateEmailTemplate.generateConfirmMailTemplate(mailValidationUrl,emailValidationToken);
        emailSenderService.sendEmail("nizar.al.nasraoui@gmail.com",
                "Confirmation de l'adresse e-mail",
                emailBody);
    }
}
