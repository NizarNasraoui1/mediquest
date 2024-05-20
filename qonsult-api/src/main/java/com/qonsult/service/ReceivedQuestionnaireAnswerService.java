package com.qonsult.service;

import com.qonsult.dto.ReceivedQuestionnaireDTO;

import java.time.LocalDate;
import java.util.List;

public interface ReceivedQuestionnaireAnswerService {
    List<ReceivedQuestionnaireDTO> getQuestionnaireAnswersByAppointmentDate(LocalDate appointmentDate);
}
