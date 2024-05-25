package com.qonsult.service.impl;

import com.qonsult.dto.QuestionnaireModelDTO;
import com.qonsult.dto.QuestionnaireRequestDTO;
import com.qonsult.entity.QuestionnaireRequest;
import com.qonsult.enumeration.QuestionnaireRequestStateEnum;
import com.qonsult.exception.QuestionnaireAlreadyPassedException;
import com.qonsult.mapper.QuestionnaireModelMapper;
import com.qonsult.repository.QuestionnaireRequestRepository;
import com.qonsult.service.QuestionnaireRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionnaireRequestServiceImpl implements QuestionnaireRequestService {

    private final QuestionnaireRequestRepository questionnaireRequestRepository;
    private final QuestionnaireModelMapper questionnaireModelMapper;
    public void saveQuestionnaireRequest(QuestionnaireRequestDTO questionnaireRequestDTO) {

    }

    @Override
    public QuestionnaireModelDTO getQuestionnaireModelByQuestionnaireRequestId(UUID id) throws QuestionnaireAlreadyPassedException {
        QuestionnaireRequest questionnaireRequest = questionnaireRequestRepository.findById(id).orElseThrow(()->new EntityNotFoundException("questionnaire request not found"));
        switch (questionnaireRequest.getQuestionnaireRequestState()){
            case SENT:{
                questionnaireRequest.setQuestionnaireRequestState(QuestionnaireRequestStateEnum.OPENED);
                questionnaireRequestRepository.save(questionnaireRequest);
            }
            case TERMINATED:{
                throw new QuestionnaireAlreadyPassedException("questionnaire already passed");
            }
        }
        return questionnaireModelMapper.toDto(questionnaireRequest.getQuestionnaireModel());
    }

    @Override
    public List<QuestionnaireRequest> getAllQuestionnaireRequestForQrCode() {
        return questionnaireRequestRepository.findAllByUsedForQrCodeTrue();
    }

}
