package com.qonsult.service.impl;

import com.qonsult.dto.QuestionnaireRequestDTO;
import com.qonsult.entity.QuestionnaireRequest;
import com.qonsult.generic.GenericServiceImpl;
import com.qonsult.mapper.QuestionnaireRequestMapper;
import com.qonsult.repository.QuestionnaireRequestRepository;
import com.qonsult.service.QuestionnaireRequestService;
import org.springframework.stereotype.Service;

@Service
public class QuestionnaireRequestServiceImpl extends GenericServiceImpl<QuestionnaireRequest, QuestionnaireRequestDTO,Long, QuestionnaireRequestRepository, QuestionnaireRequestMapper> implements QuestionnaireRequestService {
    public QuestionnaireRequestServiceImpl(QuestionnaireRequestRepository repository, QuestionnaireRequestMapper mapper) {
        super(repository, mapper);
    }
}
