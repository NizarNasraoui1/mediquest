package com.qonsult.resource;

import com.qonsult.dto.CreateQuestionnaireModelDTO;
import com.qonsult.service.BuildQuestionnaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questionnaire-builder")
@RequiredArgsConstructor
public class BuildQuestionnaireResource {
    private final BuildQuestionnaireService buildQuestionnaireService;

    @PostMapping
    public ResponseEntity<?>buildQuestionnaire(@RequestBody CreateQuestionnaireModelDTO createQuestionnaireModelDTO){
        buildQuestionnaireService.createQuestionnaireModal(createQuestionnaireModelDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
