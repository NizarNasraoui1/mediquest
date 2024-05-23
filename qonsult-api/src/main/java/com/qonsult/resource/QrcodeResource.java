package com.qonsult.resource;

import com.qonsult.dto.QrCodeDTO;
import com.qonsult.service.QrcodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("qr-codes")
@RequiredArgsConstructor
public class QrcodeResource {
    private final QrcodeService qrcodeService;
    @GetMapping("/all")
    public ResponseEntity<List<QrCodeDTO>>getAllQrCodes(){
        return new ResponseEntity<>(qrcodeService.getAllQrCodes(), HttpStatus.OK);
    }
}
