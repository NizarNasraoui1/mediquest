package com.qonsult.resource;

import com.qonsult.dto.QuestionnaireInvitationDTO;
import com.qonsult.dto.QuestionnaireModelDTO;
import com.qonsult.dto.QuestionnaireRequestDTO;
import com.qonsult.service.QuestionnaireRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/questionnaire-request")
@RequiredArgsConstructor
public class QuestionnaireRequestResource {

    private final QuestionnaireRequestService questionnaireRequestService;

    @PostMapping
    public ResponseEntity<?>sendQuestionnaireInvitation(@RequestBody QuestionnaireRequestDTO questionnaireRequestDTO){
        questionnaireRequestService.saveQuestionnaireRequest(questionnaireRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
