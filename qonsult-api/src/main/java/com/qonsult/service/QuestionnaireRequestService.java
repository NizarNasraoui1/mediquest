package com.qonsult.service;

import com.qonsult.dto.QuestionnaireRequestDTO;
import com.qonsult.entity.QuestionnaireRequest;
import com.qonsult.generic.GenericService;
import com.qonsult.mapper.QuestionnaireMapper;
import com.qonsult.mapper.QuestionnaireRequestMapper;
import com.qonsult.repository.QuestionnaireRepository;
import com.qonsult.repository.QuestionnaireRequestRepository;

public interface QuestionnaireRequestService {
    void saveQuestionnaireRequest(QuestionnaireRequestDTO questionnaireRequestDTO);
}
