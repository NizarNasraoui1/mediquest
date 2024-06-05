package com.qonsult.service.impl;

import com.qonsult.dto.CreateQuestionnaireModelDTO;
import com.qonsult.entity.*;
import com.qonsult.enumeration.QuestionTypeEnum;
import com.qonsult.repository.QuestionnaireModelRepository;
import com.qonsult.service.BuildQuestionnaireService;
import com.qonsult.service.QuestionnaireRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildQuestionnaireServiceImpl implements BuildQuestionnaireService {

    private final QuestionnaireModelRepository questionnaireModelRepository;

    private final QuestionnaireRequestService questionnaireRequestService;

    public void createQuestionnaireModal(CreateQuestionnaireModelDTO createQuestionnaireModelDTO){
        QuestionnaireModel questionnaireModel = new QuestionnaireModel();
        questionnaireModel.setName(createQuestionnaireModelDTO.getQuestionnaireTitle());
        List<Topic> topics = new ArrayList<>();
        questionnaireModel.setTopics(topics);
        for(CreateQuestionnaireModelDTO.Section section:createQuestionnaireModelDTO.getSections()){
            CreateQuestionnaireModelDTO.Section.Questions questions = section.getQuestions();
            Topic topic = new Topic();
            topics.add(topic);
            topic.setName(questions.getSectionTitle());
            List<Question>questionList = new ArrayList<>();
            topic.setQuestions(questionList);
            for (CreateQuestionnaireModelDTO.Section.Questions.SectionQuestion question : questions.getSectionQuestions()) {
                Question.QuestionBuilder questionnaireModelQuestion = Question.builder().label(question.getQuestionTitle());
                String questionType = question.getType();
                if (questionType.equals("text")) {
                    questionnaireModelQuestion.type(QuestionTypeEnum.TEXT).label(question.getQuestionTitle());
                } else {
                    if(questionType.equals("singleSelection")){
                        questionnaireModelQuestion.type(QuestionTypeEnum.RADIO).label(question.getQuestionTitle());

                    }
                    if(questionType.equals("multipleSelection")){
                        questionnaireModelQuestion.type(QuestionTypeEnum.CHECKBOX).label(question.getQuestionTitle());
                    }
                    List<CodeLabel> content = new ArrayList<>();
                    for (String offeredAnswer : question.getOfferedAnswers()) {
                        content.add(new CodeLabel(offeredAnswer));
                    }
                    questionnaireModelQuestion.content(content);

                    List<Condition> conditions = new ArrayList<>();
                    for (int conditionRank : question.getConditions()) {
                        Condition newCondition = new Condition();
                        newCondition.setRank(conditionRank);
                        conditions.add(newCondition);
                    }
                    questionnaireModelQuestion.conditions(conditions);
                }
                questionList.add(questionnaireModelQuestion.build());
            }

        }
        questionnaireModelRepository.save(questionnaireModel);
        questionnaireRequestService.saveQuestionnaireRequestFromModel(questionnaireModel);
    }
}
