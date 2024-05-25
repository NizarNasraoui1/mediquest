package com.qonsult.service.impl;

import com.qonsult.dto.QuestionnaireInvitationDTO;
import com.qonsult.entity.QuestionnaireModel;
import com.qonsult.entity.QuestionnaireRequest;
import com.qonsult.service.EmailSenderService;
import com.qonsult.service.QuestionnaireInvitationService;
import com.qonsult.service.QuestionnaireModelService;
import com.qonsult.service.QuestionnaireRequestService;
import com.qonsult.util.QuestionnaireInvitationMailTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionnaireInvitationServiceImpl implements QuestionnaireInvitationService {

    private final EmailSenderService emailSenderService;

    private final QuestionnaireModelService questionnaireModelService;

    private final QuestionnaireRequestService questionnaireRequestService;

    @Override
    public void sendQuestionnaireInvitationRequest(QuestionnaireInvitationDTO questionnaireInvitationDTO){
        QuestionnaireModel questionnaireModel = questionnaireModelService.getQuestionnaireModelById(questionnaireInvitationDTO.getQuestionnaireModelId());
        QuestionnaireRequest questionnaireRequest = new QuestionnaireRequest();
        questionnaireRequest.setQuestionnaireModel(questionnaireModel);
        questionnaireRequest.setAppointmentDate(questionnaireInvitationDTO.getAppointmentDate());
        questionnaireRequestService.saveQuestionnaireRequest(questionnaireRequest);
        questionnaireRequest.setQuestionnaireModel(questionnaireModel);
        sendQuestionnaireInvitationMails(questionnaireInvitationDTO.getEmails(),"url");
    }
    public void sendQuestionnaireInvitationMails(List<String>mailList,String url){
        String emailBody = QuestionnaireInvitationMailTemplate.generateQuestionnaireInvitationMailTemplate(url);
        mailList.parallelStream().forEach((mail)-> emailSenderService.sendEmail(mail, "Questionnaire Médical", emailBody));
    }
}
