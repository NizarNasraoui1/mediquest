package com.qonsult.service.impl;

import com.qonsult.dto.QuestionnaireModelDTO;
import com.qonsult.entity.QuestionnaireModel;
import com.qonsult.exception.EntityException;
import com.qonsult.generic.GenericServiceImpl;
import com.qonsult.mapper.QuestionnaireModelMapper;
import com.qonsult.repository.QuestionnaireRepository;
import com.qonsult.service.QuestionnaireService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class QuestionnaireServiceImpl  extends GenericServiceImpl<QuestionnaireModel, QuestionnaireModelDTO,Long, QuestionnaireRepository, QuestionnaireModelMapper> implements QuestionnaireService {
    public QuestionnaireServiceImpl(QuestionnaireRepository repository, QuestionnaireModelMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public Mono<QuestionnaireModelDTO> getQuestionnaireByName(String name) {
        return Mono.justOrEmpty(repository.findByName(name))
                .switchIfEmpty(Mono.error(new EntityException("not found")))
                .map(mapper::toDto);
    }

    public List<QuestionnaireModel> getAllQuestionnaires(){
        return repository.findAll();
    }

    public QuestionnaireModel getQuestionnaireById(Long id){
        return repository.findById(id).orElseThrow(()->new EntityNotFoundException("questionnaire not found"));
    }

}
