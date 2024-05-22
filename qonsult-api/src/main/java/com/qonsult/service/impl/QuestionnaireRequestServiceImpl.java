package com.qonsult.service.impl;

import com.qonsult.dto.QuestionnaireRequestDTO;
import com.qonsult.repository.QuestionnaireRequestRepository;
import com.qonsult.service.QuestionnaireRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionnaireRequestServiceImpl implements QuestionnaireRequestService {

    private final QuestionnaireRequestRepository questionnaireRequestRepository;
    public void saveQuestionnaireRequest(QuestionnaireRequestDTO questionnaireRequestDTO) {

    }

}
