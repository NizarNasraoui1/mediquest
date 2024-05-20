package com.qonsult.service;

import com.qonsult.dto.SuccessResponseDTO;
import reactor.core.publisher.Mono;

public interface InitAccount {
    Mono<SuccessResponseDTO> initAccount();
}
