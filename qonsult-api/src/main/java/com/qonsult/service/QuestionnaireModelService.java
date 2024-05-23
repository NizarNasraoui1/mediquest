package com.qonsult.service;

import com.qonsult.dto.QuestionnaireLinkDTO;
import com.qonsult.entity.QuestionnaireModel;

import java.util.List;

public interface QuestionnaireModelService {
    List<QuestionnaireLinkDTO> getQuestionnaireNamesAndLinks();

    List<QuestionnaireModel> findAll();
}
