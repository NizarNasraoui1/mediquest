package com.qonsult.service;

import com.qonsult.dto.QrCodeDTO;

import java.util.List;

public interface QrcodeService {
    List<QrCodeDTO> getAllQrCodes();
}
