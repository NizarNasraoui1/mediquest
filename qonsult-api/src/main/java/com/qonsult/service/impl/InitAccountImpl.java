package com.qonsult.service.impl;

import com.qonsult.dto.SuccessResponseDTO;
import com.qonsult.entity.CenterUser;
import com.qonsult.init.InitQuestionnaires;
import com.qonsult.service.CenterUserService;
import com.qonsult.service.InitAccount;
import com.qonsult.service.QuestionnaireModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class InitAccountImpl implements InitAccount {

    private final QuestionnaireModelService questionnaireModelService;
    private final CenterUserService centerUserService;
    private final InitQuestionnaires initQuestionnaires;

    public Mono<SuccessResponseDTO> initAccount() {
        Mono<Boolean> isQuestionnaireInitialized = isQuestionnaireInitialized();
        Mono<Boolean> isUserSaved = isUserSaved(getUserName());

        return isQuestionnaireInitialized.flatMap(questionnaireInitialized -> {
            if (!questionnaireInitialized) {
                initQuestionnaires.init();
            }
            return isUserSaved;
        }).flatMap(userSaved -> {
            if (!userSaved) {
                CenterUser centerUser = new CenterUser();
                centerUser.setUsername(getUserName());
                return centerUserService.saveEntity(centerUser).thenReturn(new SuccessResponseDTO("Account Initialized"));
            }
            return Mono.just(new SuccessResponseDTO("Account Already Initialized"));
        });
    }


    public Mono<Boolean> isQuestionnaireInitialized(){
        return Flux.just(questionnaireModelService.findAll()).hasElements();
    }

    public String getUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }
    public Mono<Boolean> isUserSaved(String username){
        return centerUserService.findCenterUserByUsername(username).hasElement();
    }
}
