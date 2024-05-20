package com.qonsult.resource;

import com.qonsult.dto.QuestionnaireViewDTO;
import com.qonsult.service.QuestionnaireViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/view-questionnaire")
@RequiredArgsConstructor
public class QuestionnaireViewResource {
    private final QuestionnaireViewService questionnaireViewService;

    @GetMapping("/{id}")
    public ResponseEntity<QuestionnaireViewDTO>viewQuestionnaireById(@PathVariable("id")Long id){
        return new ResponseEntity<>(questionnaireViewService.viewQuestionnaireById(id), HttpStatus.OK);
    }
}
