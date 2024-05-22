package com.qonsult.service;

import com.qonsult.dto.QuestionnaireViewDTO;

import java.util.UUID;

public interface QuestionnaireViewService {
    QuestionnaireViewDTO viewQuestionnaireByQuestionnaireResponseId(UUID id);
}
