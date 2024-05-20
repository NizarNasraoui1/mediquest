package com.qonsult.mapper;

import com.qonsult.dto.QuestionnaireLinkDTO;
import com.qonsult.entity.Questionnaire;

import java.util.List;

public interface QuestionnaireLinkMapper {
    QuestionnaireLinkDTO toDto(Questionnaire questionnaireLink);
    List<QuestionnaireLinkDTO> toDtos(List<Questionnaire>questionnaireLinks);

    String buildQuestionnaireLink(Questionnaire questionnaire);
}
