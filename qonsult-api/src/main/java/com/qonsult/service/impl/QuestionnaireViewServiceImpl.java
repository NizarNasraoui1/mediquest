package com.qonsult.service.impl;

import com.qonsult.dto.*;
import com.qonsult.entity.Question;
import com.qonsult.entity.QuestionAnswer;
import com.qonsult.entity.Questionnaire;
import com.qonsult.entity.QuestionnaireAnswer;
import com.qonsult.enumeration.QuestionTypeEnum;
import com.qonsult.mapper.PatientInformationMapper;
import com.qonsult.repository.QuestionnaireAnswerRepository;
import com.qonsult.service.QuestionnaireAnswerService;
import com.qonsult.service.QuestionnaireViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionnaireViewServiceImpl implements QuestionnaireViewService {
    private final QuestionnaireAnswerRepository questionnaireAnswerRepository;
    private final PatientInformationMapper patientInformationMapper;

    public QuestionnaireViewDTO viewQuestionnaireById(Long id){
        QuestionnaireAnswer questionnaireAnswer = questionnaireAnswerRepository.findById(id).orElseThrow(()->new EntityNotFoundException("questionnaire not found"));
        List<QuestionAnswer> questionAnswers = questionnaireAnswer.getQuestionAnswers();
        Questionnaire questionnaire = questionnaireAnswer.getQuestionnaire();
        QuestionnaireViewDTO questionnaireViewDTO = new QuestionnaireViewDTO();
        questionnaireViewDTO.setName(questionnaire.getName());
        PatientInformationDTO patientInformationDTO = patientInformationMapper.toDto(questionnaireAnswer.getPatientInformation());
        questionnaireViewDTO.setPatientInformations(patientInformationDTO);
        SignatureDTO signatureDTO = new SignatureDTO();
        questionnaireViewDTO.setSignature(signatureDTO);
        signatureDTO.setClickX(questionnaireAnswer.getCertification().getClickX());
        signatureDTO.setClickY(questionnaireAnswer.getCertification().getClickY());
        signatureDTO.setClickDrag(questionnaireAnswer.getCertification().getClickDrag());
        questionnaire.getTopics().forEach((topic -> {
            QuestionnaireViewTopicDTO questionnaireViewTopicDTO = new QuestionnaireViewTopicDTO();
            questionnaireViewDTO.getTopics().add(questionnaireViewTopicDTO);
            questionnaireViewTopicDTO.setName(topic.getName());
            topic.getQuestions().forEach(question -> {
                QuestionViewResponseDTO questionViewResponseDTO = new QuestionViewResponseDTO();
                questionnaireViewTopicDTO.getContent().add(questionViewResponseDTO);
                questionViewResponseDTO.setQuestionType(question.getType());
                questionViewResponseDTO.setQuestion(question.getLabel());
                questionViewResponseDTO.setResponse(getAnswersByQuestionId(question.getId(),questionAnswers,question));
            });
        }));
        return questionnaireViewDTO;
    }

    public List<String>getAnswersByQuestionId(Long id, List<QuestionAnswer> questionAnswers, Question question){
        return questionAnswers.stream().filter((questionAnswer -> Long.parseLong(questionAnswer.getQuestionId())==id))
                .map(QuestionAnswer::getContent)
                .map(answerCode -> transformCodetoLabel(answerCode,question))
                .findAny()
                .orElse(null);
    }

    public List<String> transformCodetoLabel(List<String> codes,Question question){
        return codes.stream().map(code->findLabelByCode(code,question)).collect(Collectors.toList());
    }
    public String findLabelByCode(String code,Question question){
        if(code.isEmpty()){
            return null;
        }
        if(question.getType()== QuestionTypeEnum.NUMBER || question.getType()==QuestionTypeEnum.TEXT){
            return code;
        }
        if(question.getType()==QuestionTypeEnum.RADIO || question.getType()==QuestionTypeEnum.CHECKBOX){
            return question.getContent().stream().filter(content->content.getId()==Long.parseLong(code))
                    .findAny().get().getLabel();
        }
        return null;

    }
}
