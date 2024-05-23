package com.qonsult.resource;

import com.qonsult.dto.QuestionnaireResponseDTO;
import com.qonsult.service.QuestionnaireResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/questionnaire-response")
@RequiredArgsConstructor
public class QuestionnaireResponsePublicResource {
    private final QuestionnaireResponseService questionnaireResponseService;

    @PostMapping
    public ResponseEntity<?>saveQuestionnaireResponse(@RequestBody QuestionnaireResponseDTO questionnaireResponseDTO){
        questionnaireResponseService.saveQuestionnaireResponse(questionnaireResponseDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
