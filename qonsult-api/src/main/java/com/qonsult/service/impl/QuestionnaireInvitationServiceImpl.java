package com.qonsult.service.impl;

import com.qonsult.dto.QuestionnaireInvitationDTO;
import com.qonsult.entity.QuestionnaireInvitation;
import com.qonsult.mapper.QuestionnaireInvitationMapper;
import com.qonsult.mapper.QuestionnaireLinkMapper;
import com.qonsult.mapper.QuestionnaireMapper;
import com.qonsult.repository.QuestionnaireInvitationRepository;
import com.qonsult.service.EmailSenderService;
import com.qonsult.service.QuestionnaireInvitationService;
import com.qonsult.util.QuestionnaireInvitationMailTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionnaireInvitationServiceImpl implements QuestionnaireInvitationService {

    private final QuestionnaireInvitationRepository questionnaireInvitationRepository;

    private final QuestionnaireInvitationMapper questionnaireInvitationMapper;

    private final EmailSenderService emailSenderService;

    private final QuestionnaireLinkMapper questionnaireLinkMapper;

    private final QuestionnaireMapper questionnaireMapper;

    @Override
    public QuestionnaireInvitationDTO hundleQuestionnaireInvitationRequest(QuestionnaireInvitationDTO questionnaireInvitationDTO){
        sendQuestionnaireInvitationMails(questionnaireInvitationDTO.getEmails(),questionnaireLinkMapper.buildQuestionnaireLink(questionnaireMapper.toBo(questionnaireInvitationDTO.getQuestionnaire())));
        return questionnaireInvitationMapper.toDto(saveQuestionnaireInvitation(questionnaireInvitationMapper.toBo(questionnaireInvitationDTO)));
    }

    public QuestionnaireInvitation saveQuestionnaireInvitation(QuestionnaireInvitation questionnaireInvitation){
        return questionnaireInvitationRepository.save(questionnaireInvitation);
    }

    public void sendQuestionnaireInvitationMails(List<String>mailList,String url){
        String emailBody = QuestionnaireInvitationMailTemplate.generateQuestionnaireInvitationMailTemplate(url);
        mailList.parallelStream().forEach((mail)-> emailSenderService.sendEmail(mail, "Questionnaire Médical", emailBody));
    }
}
