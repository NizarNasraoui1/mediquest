package com.qonsult.resource;

import com.qonsult.dto.QuestionnaireInvitationDTO;
import com.qonsult.service.QuestionnaireInvitationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/questionnaire-invitation")
@RequiredArgsConstructor
public class QuestionnaireInvitationResource {

    private final QuestionnaireInvitationService questionnaireInvitationService;

    @PostMapping
    public ResponseEntity<QuestionnaireInvitationDTO>sendQuestionnaireInvitation(@RequestBody QuestionnaireInvitationDTO questionnaireInvitationDTO){
        return new ResponseEntity<>(questionnaireInvitationService.hundleQuestionnaireInvitationRequest(questionnaireInvitationDTO), HttpStatus.OK);
    }
}
