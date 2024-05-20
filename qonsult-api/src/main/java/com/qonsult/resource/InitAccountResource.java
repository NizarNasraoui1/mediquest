package com.qonsult.resource;

import com.qonsult.dto.SuccessResponseDTO;
import com.qonsult.service.InitAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/init-account")
@RequiredArgsConstructor
public class InitAccountResource {
    private final InitAccount initAccount;
    @PostMapping
    public Mono<SuccessResponseDTO> initAccount(){
        return initAccount.initAccount();
    }

}
