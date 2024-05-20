package com.qonsult.service;

import com.qonsult.dto.QuestionnaireDTO;
import com.qonsult.entity.Questionnaire;
import com.qonsult.generic.GenericService;
import com.qonsult.mapper.QuestionnaireMapper;
import com.qonsult.repository.QuestionnaireRepository;
import reactor.core.publisher.Mono;

import java.util.List;

public interface QuestionnaireService extends GenericService<Questionnaire, QuestionnaireDTO,Long, QuestionnaireRepository, QuestionnaireMapper> {
    Mono<QuestionnaireDTO> getQuestionnaireByName(String name);
    List<Questionnaire> getAllQuestionnaires();
    Questionnaire getQuestionnaireById(Long id);
}
