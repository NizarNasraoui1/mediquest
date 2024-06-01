package com.qonsult.service;

import com.qonsult.dto.*;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface AuthService {
    Mono<AuthResponseDTO> authenticate(AuthRequest authRequest) throws Exception;
    Mono<Void> processForgotPassword(String userEmail);

    Mono<Void>changePassword(ResetPasswordRequestDTO resetPasswordRequestDTO);

    void register(RegisterDTO registerDTO) throws Exception;

    Mono<Void> validateMail(String validationToken);

    Mono<AuthResponseDTO> getAccessTokenFromRefreshToken(String accessToken);
}
