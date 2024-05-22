//package com.qonsult.service.impl;
//
//import com.qonsult.dto.QuestionnaireLinkDTO;
//import com.qonsult.entity.QuestionnaireModel;
//import com.qonsult.entity.QuestionnaireLink;
//import com.qonsult.mapper.QuestionnaireLinkMapper;
//import com.qonsult.repository.QuestionnaireLinkRepository;
//import com.qonsult.repository.QuestionnaireRepository;
//import com.qonsult.service.QuestionnaireLinkService;
//import com.qonsult.service.QuestionnaireService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class QuestionnaireLinkServiceImpl  implements QuestionnaireLinkService {
//
//    private final QuestionnaireService questionnaireService;
//    private final QuestionnaireLinkRepository questionnaireLinkRepository;
//    private final QuestionnaireRepository questionnaireRepository;
//    private final QuestionnaireLinkMapper questionnaireLinkMapper;
//    public QuestionnaireLinkDTO saveQuestionnireLink(Long questionnaireId) {
//        QuestionnaireModel questionnaireModel = questionnaireService.getQuestionnaireById(questionnaireId);
//        QuestionnaireLink questionnaireLink = new QuestionnaireLink();
//        questionnaireLink.setQuestionnaireModel(questionnaireModel);
//        questionnaireLinkRepository.save(questionnaireLink);
//        return questionnaireLinkMapper.toDto(questionnaireModel);
//    }
//
//    public List<QuestionnaireLinkDTO> getAllStoreduestionnaireLinks(){
//        return questionnaireLinkMapper.toDtos(questionnaireLinkRepository.findAll().stream().map(QuestionnaireLink::getQuestionnaireModel).collect(Collectors.toList()));
//    }
//
//    public List<QuestionnaireLinkDTO> getAllQuestionnaireLinks(){
//        return questionnaireLinkMapper.toDtos(questionnaireRepository.findAll());
//    }
//
//}
