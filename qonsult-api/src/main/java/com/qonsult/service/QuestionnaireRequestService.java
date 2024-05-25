package com.qonsult.service;

import com.qonsult.dto.QuestionnaireModelDTO;
import com.qonsult.dto.QuestionnaireRequestDTO;
import com.qonsult.entity.QuestionnaireRequest;
import com.qonsult.exception.QuestionnaireAlreadyPassedException;

import java.util.List;
import java.util.UUID;

public interface QuestionnaireRequestService {
    void saveQuestionnaireRequest(QuestionnaireRequestDTO questionnaireRequestDTO);

    QuestionnaireModelDTO getQuestionnaireModelByQuestionnaireRequestId(UUID id) throws QuestionnaireAlreadyPassedException;

    List<QuestionnaireRequest> getAllQuestionnaireRequestForQrCode();
}
