package com.qonsult.resource;

import com.qonsult.dto.ReceivedQuestionnaireDTO;
import com.qonsult.service.QuestionnaireResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/received-questionnaires")
@RequiredArgsConstructor
public class ReceivedQuestionnaireAnswerResource {
    private final QuestionnaireResponseService questionnaireResponseService;

    @GetMapping
    public ResponseEntity<List<ReceivedQuestionnaireDTO>>getReceivedQuestionnaires(@RequestParam(name = "date") LocalDate date){
        return new ResponseEntity<>(questionnaireResponseService.getQuestionnaireAnswersByAppointmentDate(date), HttpStatus.OK);
    }
}
