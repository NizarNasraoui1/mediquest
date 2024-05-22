package com.qonsult.service.impl;

import com.qonsult.dto.ReceivedQuestionnaireDTO;
import com.qonsult.entity.PatientInformation;
import com.qonsult.entity.QuestionnaireRequest;
import com.qonsult.entity.QuestionnaireResponse;
import com.qonsult.repository.QuestionnaireRequestRepository;
import com.qonsult.repository.QuestionnaireResponseRepository;
import com.qonsult.service.QuestionnaireResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionnaireResponseServiceImpl implements QuestionnaireResponseService {
    private QuestionnaireResponseRepository questionnaireResponseRepository;
    private QuestionnaireRequestRepository questionnaireRequestRepository;
    public List<ReceivedQuestionnaireDTO>getQuestionnaireAnswersByAppointmentDate(LocalDate appointmentDate){
        List<QuestionnaireRequest> questionnaireRequests = questionnaireRequestRepository.findAllByAppointmentDate(appointmentDate);
        return questionnaireRequests.stream().map(questionnaireRequest -> {
            QuestionnaireResponse questionnaireResponse = questionnaireRequest.getQuestionnaireResponse();
            ReceivedQuestionnaireDTO receivedQuestionnaireDTO = new ReceivedQuestionnaireDTO();
            receivedQuestionnaireDTO.setQuestionnaireResponseId(questionnaireResponse.getId());
            PatientInformation patientInformation = questionnaireResponse.getPatientInformation();
            String name = new StringJoiner(patientInformation.getLastName(),patientInformation.getFirstName()," ").toString();
            receivedQuestionnaireDTO.setName(name);
            receivedQuestionnaireDTO.setQuestionnaireRequestState(questionnaireRequest.getQuestionnaireRequestState());
            receivedQuestionnaireDTO.setReceptionDate(questionnaireResponse.getSignedDate());
            return receivedQuestionnaireDTO;
        }).collect(Collectors.toList());
    }



}
