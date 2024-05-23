package com.qonsult.service.impl;

import com.qonsult.dto.QuestionnaireResponseDTO;
import com.qonsult.dto.ReceivedQuestionnaireDTO;
import com.qonsult.entity.PatientInformation;
import com.qonsult.entity.QuestionnaireRequest;
import com.qonsult.entity.QuestionnaireResponse;
import com.qonsult.mapper.QuestionnaireResponseMapper;
import com.qonsult.repository.QuestionnaireRequestRepository;
import com.qonsult.repository.QuestionnaireResponseRepository;
import com.qonsult.service.QuestionnaireResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionnaireResponseServiceImpl implements QuestionnaireResponseService {

    private final QuestionnaireResponseRepository questionnaireResponseRepository;

    private final QuestionnaireRequestRepository questionnaireRequestRepository;

    private final QuestionnaireResponseMapper questionnaireResponseMapper;

    @Transactional
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

    @Override
    public void saveQuestionnaireResponse(QuestionnaireResponseDTO questionnaireResponseDTO) {
        QuestionnaireResponse questionnaireResponse = questionnaireResponseMapper.toBo(questionnaireResponseDTO);
        QuestionnaireRequest questionnaireRequest = questionnaireRequestRepository.findById(questionnaireResponseDTO.getQuestionnaireRequestId()).orElseThrow(()-> new EntityNotFoundException("questionnaire request not found"));
        questionnaireResponse.setQuestionnaireRequest(questionnaireRequest);
        questionnaireResponseRepository.save(questionnaireResponse);
    }


}
