package com.qonsult.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.qonsult.dto.AuthRequest;
import com.qonsult.dto.AuthResponseDTO;
import com.qonsult.dto.RegisterDTO;
import com.qonsult.dto.ResetPasswordRequestDTO;
import com.qonsult.entity.auth.Group;
import com.qonsult.entity.auth.User;
import com.qonsult.entity.auth.ValidationToken;
import com.qonsult.exception.EntityException;
import com.qonsult.repository.UserRepository;
import com.qonsult.repository.ValidationTokenRepository;
import com.qonsult.service.*;
import com.qonsult.util.ForgotPasswordEmailTemplate;
import com.qonsult.util.ValidateEmailTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.openssl.PasswordException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ValidationTokenRepository validationTokenRepository;
    private final EmailSenderService emailSenderService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final InitAccountService initAccountService;
    @Value("${reset-password-url}")
    private String resetPasswordUrl;
    @Value("${mail-validation-url}")
    private String mailValidationUrl;
    private final int ACCESS_TOKEN_DURATION = 60 * 60 * 1000;
    private final int REFRESH_TOKEN_DURATION = 15 * 24 * 60 * 60 * 1000;


    @Override
    public AuthResponseDTO authenticate(AuthRequest authRequest) throws Exception {
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();
        User user = userService.loadUserByUsername(authRequest.getUsername());
        if (user == null) {
            log.error("User " + username + " not found in the database");
            throw new UsernameNotFoundException("bad credentials");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            log.error("password for user " + user.getUsername().toString() + " is wrong");
            throw new PasswordException("bad credentials");
        }
        if(!user.isEmailChecked()){
            throw new Exception("mail not checked");
        }
        log.info("User found in the database: {}", username);
        List<String> roles = new ArrayList<>();
        Group group = user.getGroup();
        group.getRoles().forEach(role -> {
            roles.add(role.getName());
        });
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String schema = user.getGroup().getAccount().getSchema().getName();


        String accessToken = generateToken(user.getUsername(), roles, schema,algorithm, ACCESS_TOKEN_DURATION);
        String refreshToken = generateToken(user.getUsername(), roles, schema,algorithm,REFRESH_TOKEN_DURATION);

        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setAccessToken(accessToken);
        authResponseDTO.setRefreshToken(refreshToken);
        authResponseDTO.setTenant(schema);
        return authResponseDTO;

    }

    public String generateToken(String username, List<String> authorities, String tenant, Algorithm algorithm, int duration) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + duration))
                .withClaim("permissions", authorities)
                .withClaim("tenant", tenant)
                .sign(algorithm);
    }

    public AuthResponseDTO getAccessTokenFromRefreshToken(String refreshToken) {
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(refreshToken);
        String username = decodedJWT.getSubject();

        User user = userService.loadUserByUsername(username);
        log.info("User found in the database: {}", username);
        List<String> roles = new ArrayList<>();
        Group group = user.getGroup();
        group.getRoles().forEach(role -> {
            roles.add(role.getName());
        });
        String schema = user.getGroup().getAccount().getSchema().getName();

        String accessToken = generateToken(user.getUsername(), roles, schema, algorithm, ACCESS_TOKEN_DURATION);

        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setAccessToken(accessToken);
        authResponseDTO.setRefreshToken(refreshToken); // Use the existing refresh token
        authResponseDTO.setTenant(schema);
        return authResponseDTO;
    }

    @Override
    public Mono<Void> processForgotPassword(String userEmail) {
        return Mono.justOrEmpty(userRepository.findByEmail(userEmail))
                .switchIfEmpty(Mono.error(new EntityException("not found")))
                .flatMap(user -> {
                            ValidationToken token = new ValidationToken();
                            String emailToken = UUID.randomUUID().toString();
                            token.setToken(emailToken);
                            token.setUser(user);
                            token.setExpiryDate(Instant.ofEpochSecond(3600));
                            validationTokenRepository.save(token);
                            return Mono.justOrEmpty(emailToken);
                        }
                )
                .flatMap(emailToken -> {
                    String emailBody = ForgotPasswordEmailTemplate.generateForgotMailTemplate(resetPasswordUrl, emailToken);
                    emailSenderService.sendEmail("nizar.al.nasraoui@gmail.com",
                            "Réinitialisation de mot de passe",
                            emailBody);
                    return Mono.empty();
                });
    }

    @Override
    public Mono<Void> changePassword(ResetPasswordRequestDTO resetPasswordRequestDTO) {
        return Mono.justOrEmpty(validationTokenRepository.findByToken(resetPasswordRequestDTO.getResetPasswordToken()))
                .switchIfEmpty(Mono.error(new EntityException("not found")))
                .flatMap((token) -> {
                    User user = token.getUser();
                    user.setPassword(passwordEncoder.encode(resetPasswordRequestDTO.getPassword()));
                    userRepository.save(user);
                    return Mono.empty();
                });
    }

    @Override
    public Mono<Void> validateMail(String validationToken) {
        return Mono.justOrEmpty(validationTokenRepository.findByToken(validationToken))
                .switchIfEmpty(Mono.error(new EntityException("not found")))
                .flatMap((token) -> {
                    User user = token.getUser();
                    user.setEmailChecked(true);
                    userRepository.save(user);
                    return Mono.empty();
                });
    }


    @Override
    public void register(RegisterDTO registerDTO) throws Exception {
        initAccountService.register(registerDTO);
    }

    public void emailVerification(User user) {
        ValidationToken validationToken = new ValidationToken();
        validationToken.setUser(user);
        String emailValidationToken = UUID.randomUUID().toString();
        validationToken.setToken(emailValidationToken);
        validationTokenRepository.save(validationToken);
        String emailBody = ValidateEmailTemplate.generateConfirmMailTemplate(mailValidationUrl, emailValidationToken);
        emailSenderService.sendEmail("nizar.al.nasraoui@gmail.com",
                "Confirmation de l'adresse e-mail",
                emailBody);
    }
}
