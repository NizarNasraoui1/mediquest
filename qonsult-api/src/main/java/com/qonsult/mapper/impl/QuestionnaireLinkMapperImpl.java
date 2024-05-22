//package com.qonsult.mapper.impl;
//
//import com.qonsult.config.tenant_config.TenantContext;
//import com.qonsult.dto.QuestionnaireLinkDTO;
//import com.qonsult.entity.QuestionnaireModel;
//import com.qonsult.mapper.QuestionnaireLinkMapper;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class QuestionnaireLinkMapperImpl implements QuestionnaireLinkMapper {
//    @Value("${questionnaire-fe-link}")
//    private String questionnaireLink;
//    @Override
//    public QuestionnaireLinkDTO toDto(QuestionnaireModel questionnaireModel) {
//        // url format : questionnaire/schema-name/questionnaire-id
//        if(questionnaireModel ==null){
//            return null;
//        }
//        QuestionnaireLinkDTO questionnaireLinkDTO = new QuestionnaireLinkDTO();
//        questionnaireLinkDTO.setId(questionnaireModel.getId());
//        questionnaireLinkDTO.setName(questionnaireModel.getName());
//        String url = buildQuestionnaireLink(questionnaireModel);
//        questionnaireLinkDTO.setUrl(url);
//        return questionnaireLinkDTO;
//
//    }
//
//    public String buildQuestionnaireLink(QuestionnaireModel questionnaireModel){
//        String schemaName = TenantContext.getCurrentTenant();
//        StringBuilder sb = new StringBuilder(questionnaireLink);
//        sb.append(schemaName);
//        sb.append("/"+ questionnaireModel.getId());
//        return sb.toString();
//    }
//
//    public List<QuestionnaireLinkDTO> toDtos(List<QuestionnaireModel> questionnaireModels){
//        if(questionnaireModels ==null){
//            return null;
//        }
//        return questionnaireModels.stream().map(this::toDto).collect(Collectors.toList());
//    }
//}
