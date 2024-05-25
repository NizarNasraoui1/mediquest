package com.qonsult.resource;

import com.qonsult.dto.QuestionnaireInvitationDTO;
import com.qonsult.dto.QuestionnaireModelDTO;
import com.qonsult.dto.QuestionnaireRequestDTO;
import com.qonsult.service.QuestionnaireInvitationService;
import com.qonsult.service.QuestionnaireRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/questionnaire-invitation")
@RequiredArgsConstructor
public class QuestionnaireRequestResource {

    private final QuestionnaireInvitationService questionnaireInvitationService;

    @PostMapping
    public ResponseEntity<?>sendQuestionnaireInvitation(@RequestBody QuestionnaireInvitationDTO questionnaireInvitationDTO){
        questionnaireInvitationService.sendQuestionnaireInvitationRequest(questionnaireInvitationDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
