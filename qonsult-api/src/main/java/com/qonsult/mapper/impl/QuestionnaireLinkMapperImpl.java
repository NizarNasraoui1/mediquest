package com.qonsult.mapper.impl;

import com.qonsult.config.tenant_config.TenantContext;
import com.qonsult.dto.QuestionnaireLinkDTO;
import com.qonsult.entity.Questionnaire;
import com.qonsult.mapper.QuestionnaireLinkMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionnaireLinkMapperImpl implements QuestionnaireLinkMapper {
    @Value("${questionnaire-fe-link}")
    private String questionnaireLink;
    @Override
    public QuestionnaireLinkDTO toDto(Questionnaire questionnaire) {
        // url format : questionnaire/schema-name/questionnaire-id
        if(questionnaire==null){
            return null;
        }
        QuestionnaireLinkDTO questionnaireLinkDTO = new QuestionnaireLinkDTO();
        questionnaireLinkDTO.setId(questionnaire.getId());
        questionnaireLinkDTO.setName(questionnaire.getName());
        String url = buildQuestionnaireLink(questionnaire);
        questionnaireLinkDTO.setUrl(url);
        return questionnaireLinkDTO;

    }

    public String buildQuestionnaireLink(Questionnaire questionnaire){
        String schemaName = TenantContext.getCurrentTenant();
        StringBuilder sb = new StringBuilder(questionnaireLink);
        sb.append(schemaName);
        sb.append("/"+ questionnaire.getId());
        return sb.toString();
    }

    public List<QuestionnaireLinkDTO> toDtos(List<Questionnaire>questionnaires){
        if(questionnaires==null){
            return null;
        }
        return questionnaires.stream().map(this::toDto).collect(Collectors.toList());
    }
}
