package com.qonsult.service.impl;

import com.qonsult.dto.ReceivedQuestionnaireDTO;
import com.qonsult.service.QuestionnaireAnswerService;
import com.qonsult.service.ReceivedQuestionnaireAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReceivedQuestionnaireAnswerServiceImpl implements ReceivedQuestionnaireAnswerService {

    private final QuestionnaireAnswerService questionnaireAnswerService;
    public List<ReceivedQuestionnaireDTO> getQuestionnaireAnswersByAppointmentDate(LocalDate appointmentDate) {
        return questionnaireAnswerService.getQuestionnaireAnswersByAppointmentDate(appointmentDate).stream()
                .map((questionnaireAnswer) -> {
                    ReceivedQuestionnaireDTO receivedQuestionnaireDTO = new ReceivedQuestionnaireDTO();
                    receivedQuestionnaireDTO.setName(questionnaireAnswer.getPatientInformation().getFirstName() + " " + questionnaireAnswer.getPatientInformation().getLastName());
                    receivedQuestionnaireDTO.setReceptionDate(questionnaireAnswer.getCreatedDate());
                    receivedQuestionnaireDTO.setQuestionnaireAnswerId(questionnaireAnswer.getId());
                    return receivedQuestionnaireDTO;
                })
                .collect(Collectors.toList());
    }
}
