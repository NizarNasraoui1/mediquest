package com.qonsult.service;

import com.qonsult.dto.QuestionnaireModelDTO;
import com.qonsult.entity.QuestionnaireModel;
import com.qonsult.generic.GenericService;
import com.qonsult.mapper.QuestionnaireModelMapper;
import com.qonsult.repository.QuestionnaireRepository;
import reactor.core.publisher.Mono;

import java.util.List;

public interface QuestionnaireService extends GenericService<QuestionnaireModel, QuestionnaireModelDTO,Long, QuestionnaireRepository, QuestionnaireModelMapper> {
    Mono<QuestionnaireModelDTO> getQuestionnaireByName(String name);
    List<QuestionnaireModel> getAllQuestionnaires();
    QuestionnaireModel getQuestionnaireById(Long id);
}
