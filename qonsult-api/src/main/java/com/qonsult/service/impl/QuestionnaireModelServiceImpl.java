package com.qonsult.service.impl;

import com.qonsult.dto.QuestionnaireLinkDTO;
import com.qonsult.entity.QuestionnaireModel;
import com.qonsult.repository.QuestionnaireModelRepository;
import com.qonsult.service.QuestionnaireModelService;
import com.qonsult.service.QuestionnaireRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionnaireModelServiceImpl implements QuestionnaireModelService {

    private final QuestionnaireModelRepository questionnaireModelRepository;

    @Value("${questionnaire-fe-link}")
    String questionnaireLink;

    private final QuestionnaireRequestService questionnaireRequestService;

    @Override
    @Transactional
    public List<QuestionnaireLinkDTO> getQuestionnaireNamesAndLinks() {
        return questionnaireRequestService.getAllQuestionnaireRequestForQrCode().stream().map((questionnaireRequest)->{
            QuestionnaireLinkDTO questionnaireLinkDTO = new QuestionnaireLinkDTO();
            questionnaireLinkDTO.setName(questionnaireRequest.getQuestionnaireModel().getName());
            questionnaireLinkDTO.setQuestionnaireRequest(questionnaireRequest.getId());
            questionnaireLinkDTO.setQuestionnaireModelId(questionnaireRequest.getQuestionnaireModel().getId());
            return questionnaireLinkDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<QuestionnaireModel> findAll() {
        return questionnaireModelRepository.findAll();
    }

    public QuestionnaireModel getQuestionnaireModelById(Long id){
        return questionnaireModelRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("questionnaire model not found"));
    }
}
