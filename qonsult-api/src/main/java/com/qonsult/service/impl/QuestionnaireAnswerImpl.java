package com.qonsult.service.impl;

import com.qonsult.dto.QuestionnaireAnswerDTO;
import com.qonsult.dto.ReceivedQuestionnaireDTO;
import com.qonsult.entity.QuestionnaireAnswer;
import com.qonsult.generic.GenericServiceImpl;
import com.qonsult.mapper.QuestionnaireAnswerMapper;
import com.qonsult.repository.QuestionnaireAnswerRepository;
import com.qonsult.service.QuestionnaireAnswerService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class QuestionnaireAnswerImpl extends GenericServiceImpl<QuestionnaireAnswer, QuestionnaireAnswerDTO,Long, QuestionnaireAnswerRepository, QuestionnaireAnswerMapper> implements QuestionnaireAnswerService {
    public QuestionnaireAnswerImpl(QuestionnaireAnswerRepository repository, QuestionnaireAnswerMapper mapper) {
        super(repository, mapper);
    }
    public List<QuestionnaireAnswer>getQuestionnaireAnswersByAppointmentDate(LocalDate appointmentDate){
        return repository.findAllByAppointmentDate(appointmentDate);
    }



}
