package com.qonsult.resource;

import com.qonsult.dto.QuestionnaireLinkDTO;
import com.qonsult.service.QuestionnaireModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questionnaire-model")
@RequiredArgsConstructor
public class QuestionnaireResource {
    private final QuestionnaireModelService questionnaireModelService;

    @GetMapping("/names-links")
    public ResponseEntity<List<QuestionnaireLinkDTO>>getQuestionnaireNamesAndLinks(){
        return ResponseEntity.ok().body(questionnaireModelService.getQuestionnaireNamesAndLinks());
    }

//    @GetMapping("/{id}")
//    public Mono<QuestionnaireDTO> getQuestionnaireBySpeciality(@PathVariable("id")Long id){
//        return service.findById(id);
//    }
}
