package com.qonsult.service;

import com.qonsult.dto.QuestionnaireAnswerDTO;
import com.qonsult.entity.QuestionnaireAnswer;
import com.qonsult.generic.GenericService;
import com.qonsult.mapper.QuestionnaireAnswerMapper;
import com.qonsult.repository.QuestionnaireAnswerRepository;

import java.time.LocalDate;
import java.util.List;

public interface QuestionnaireAnswerService extends GenericService<QuestionnaireAnswer, QuestionnaireAnswerDTO,Long, QuestionnaireAnswerRepository, QuestionnaireAnswerMapper> {
    List<QuestionnaireAnswer> getQuestionnaireAnswersByAppointmentDate(LocalDate appointmentDate);
}
