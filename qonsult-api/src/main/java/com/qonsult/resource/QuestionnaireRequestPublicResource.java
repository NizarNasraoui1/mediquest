package com.qonsult.resource;

import com.qonsult.dto.QuestionnaireModelDTO;
import com.qonsult.dto.QuestionnairePassedDTO;
import com.qonsult.exception.QuestionnaireAlreadyPassedException;
import com.qonsult.service.QuestionnaireRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/public/questionnaire-request")
@RequiredArgsConstructor
public class QuestionnaireRequestPublicResource {
    private final QuestionnaireRequestService questionnaireRequestService;

    @GetMapping("/{id}/questionnaire-model")
    public ResponseEntity<?> getQuestionnaireModelByQuestionnaireRequestId(@PathVariable UUID id) {
        try{
            QuestionnaireModelDTO questionnaireModelDTO = questionnaireRequestService.getQuestionnaireModelByQuestionnaireRequestId(id);
            return ResponseEntity.ok().body(questionnaireModelDTO);
        }
        catch(QuestionnaireAlreadyPassedException questionnaireAlreadyPassedException){
            QuestionnairePassedDTO questionnairePassedDTO = questionnaireRequestService.getPassedQuestionnaireInformations(id);
            return ResponseEntity.ok(questionnairePassedDTO);
        }

    }
}
