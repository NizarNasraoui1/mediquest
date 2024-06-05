package com.qonsult.init;

import com.qonsult.entity.*;
import com.qonsult.init.models.DentistQuestionnaireModel;
import com.qonsult.repository.QuestionnaireModelRepository;
import com.qonsult.repository.QuestionnaireRequestRepository;
import com.qonsult.service.QuestionnaireRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("initDenstistQuestionnaire")
@RequiredArgsConstructor
public class InitDenstistQuestionnaire implements DBInitializer{

    private final QuestionnaireModelRepository questionnaireModelRepository;
    private final QuestionnaireRequestService questionnaireRequestService;
    private final String questionnaireName = "Questionnaire Dentiste";
    public void initQuestionnaire(){
            if(!isAlreadyInitialized()){
                QuestionnaireModel questionnaireModel = DentistQuestionnaireModel.getModel();
                questionnaireModelRepository.save(questionnaireModel);
                questionnaireRequestService.saveQuestionnaireRequestFromModel(questionnaireModel);
            }
    }

    public boolean isAlreadyInitialized(){
        return questionnaireModelRepository.findByName(questionnaireName).isPresent();
    }

    @Override
    public void init() {
        initQuestionnaire();
    }
}
