package com.qonsult.service;

import com.qonsult.dto.ReceivedQuestionnaireDTO;
import com.qonsult.entity.QuestionnaireResponse;

import java.time.LocalDate;
import java.util.List;

public interface QuestionnaireResponseService {
    List<ReceivedQuestionnaireDTO> getQuestionnaireAnswersByAppointmentDate(LocalDate appointmentDate);
}
