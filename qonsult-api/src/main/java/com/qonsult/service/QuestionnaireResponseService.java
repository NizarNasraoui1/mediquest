package com.qonsult.service;

import com.qonsult.dto.QuestionnaireResponseDTO;
import com.qonsult.dto.ReceivedQuestionnaireDTO;

import java.time.LocalDate;
import java.util.List;

public interface QuestionnaireResponseService {
    List<ReceivedQuestionnaireDTO> getQuestionnaireAnswersByAppointmentDate(LocalDate appointmentDate);
    void saveQuestionnaireResponse(QuestionnaireResponseDTO questionnaireResponseDTO);

}
