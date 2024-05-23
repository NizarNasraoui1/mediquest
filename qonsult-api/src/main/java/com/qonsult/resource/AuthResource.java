package com.qonsult.resource;

import com.qonsult.dto.*;
import com.qonsult.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("public/auth")
@RequiredArgsConstructor
public class AuthResource {
    private final AuthService authService;
    @PostMapping("/login")
    public Mono<AuthResponseDTO>login(@RequestBody AuthRequest authRequest) throws Exception {
        return authService.authenticate(authRequest);
    }

    @PostMapping("/forgot-password")
    public Mono<Void> handleForgotPassword(@RequestBody String userEmail) {
        return authService.processForgotPassword(userEmail);
    }

    @PostMapping("/reset-password")
    public Mono<Void> resetPassword(@RequestBody ResetPasswordRequestDTO resetPasswordRequestDTO){
        return authService.changePassword(resetPasswordRequestDTO);
    }

    @PostMapping("/register")
    public Mono<SuccessResponseDTO> register(@RequestBody RegisterDTO registerDTO){
        return authService.register(registerDTO);
    }

    @PostMapping("/mail-confirmation")
    public Mono<Void> validateMail(@RequestParam("token") String token){
        return authService.validateMail(token);
    }

    @PostMapping("token")
    public ResponseEntity<Mono<AuthResponseDTO>>getAccessTokenFromRefreshToken(@RequestBody String token){
        return new ResponseEntity<>(authService.getAccessTokenFromRefreshToken(token), HttpStatus.OK);
    }
}
