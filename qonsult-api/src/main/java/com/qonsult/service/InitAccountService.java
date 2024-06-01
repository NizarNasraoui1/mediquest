package com.qonsult.service;

import com.qonsult.dto.RegisterDTO;

public interface InitAccountService {
    void register(RegisterDTO registerDTO) throws Exception;
}
