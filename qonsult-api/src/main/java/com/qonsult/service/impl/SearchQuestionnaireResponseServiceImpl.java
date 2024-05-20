package com.qonsult.service.impl;

import com.qonsult.dto.PaginatedResponse;
import com.qonsult.dto.SearchQuestionnaireDTO;
import com.qonsult.entity.PatientInformation;
import com.qonsult.entity.QuestionnaireAnswer;
import com.qonsult.repository.QuestionnaireAnswerRepository;
import com.qonsult.service.SearchQuestionnaireResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SearchQuestionnaireResponseServiceImpl implements SearchQuestionnaireResponseService {

    private final QuestionnaireAnswerRepository questionnaireAnswerRepository;

    public PaginatedResponse<SearchQuestionnaireDTO> searchQuestionnaireReponse(int page, int pageSize, SearchQuestionnaireDTO searchQuestionnaireDTO){
        PageRequest pageRequest = PageRequest.of(page,pageSize);
        List<Specification<QuestionnaireAnswer>>specifications = new ArrayList<>();
        if(isValidField(searchQuestionnaireDTO.getFirstName())){
            specifications.add(((root, query, criteriaBuilder) -> {
                Join<QuestionnaireAnswer,PatientInformation> questionnaireAnswerPatientInformationJoin = root.join("patientInformation");
                return criteriaBuilder.like(criteriaBuilder.lower(questionnaireAnswerPatientInformationJoin.get("firstName")),"%"+searchQuestionnaireDTO.getFirstName().toLowerCase()+"%");
            }));

        }
        if(isValidField(searchQuestionnaireDTO.getLastName())){
            specifications.add(((root, query, criteriaBuilder) -> {
                Join<QuestionnaireAnswer,PatientInformation> questionnaireAnswerPatientInformationJoin = root.join("patientInformation");
                return criteriaBuilder.like(criteriaBuilder.lower(questionnaireAnswerPatientInformationJoin.get("lastName")),"%"+searchQuestionnaireDTO.getLastName().toLowerCase()+"%");
            }));
        }
        if(searchQuestionnaireDTO.getBirthdate()!=null){
            specifications.add(((root, query, criteriaBuilder) -> {
                Join<QuestionnaireAnswer,PatientInformation> questionnaireAnswerPatientInformationJoin = root.join("patientInformation");
                return criteriaBuilder.equal(questionnaireAnswerPatientInformationJoin.get("birthday"),searchQuestionnaireDTO.getBirthdate());
            }));
        }
        if(searchQuestionnaireDTO.getAppointmentDate()!=null){
            specifications.add(((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("appointmentDate"),searchQuestionnaireDTO.getAppointmentDate())));
        }
        Specification<QuestionnaireAnswer>questionnaireAnswerSpecification = specifications.stream().reduce(Specification::and).orElse(null);
        Page<QuestionnaireAnswer> responsePage = questionnaireAnswerRepository.findAll(questionnaireAnswerSpecification,pageRequest);
        PaginatedResponse<SearchQuestionnaireDTO> searchQuestionnaireDTOPaginatedResponse = new PaginatedResponse<>();
        searchQuestionnaireDTOPaginatedResponse.setPageSize(responsePage.getSize());
        searchQuestionnaireDTOPaginatedResponse.setTotalElements(responsePage.getTotalElements());
        searchQuestionnaireDTOPaginatedResponse.setCurrentPage(page+1);
        searchQuestionnaireDTOPaginatedResponse.setContent(
                responsePage.getContent().stream().map((questionnaireAnswer -> {
                    SearchQuestionnaireDTO searchQuestionnaireDTOResponse = new SearchQuestionnaireDTO();
                    searchQuestionnaireDTOResponse.setId(questionnaireAnswer.getId());
                    searchQuestionnaireDTOResponse.setFirstName(questionnaireAnswer.getPatientInformation().getFirstName());
                    searchQuestionnaireDTOResponse.setLastName(questionnaireAnswer.getPatientInformation().getLastName());
                    searchQuestionnaireDTOResponse.setBirthdate(questionnaireAnswer.getPatientInformation().getBirthday());
                    searchQuestionnaireDTOResponse.setAppointmentDate(questionnaireAnswer.getAppointmentDate());
                    return searchQuestionnaireDTOResponse;
                })).collect(Collectors.toList()));

        return searchQuestionnaireDTOPaginatedResponse;
    }

    public boolean isValidField(String field){
        return field != null && !field.isBlank() && !field.isBlank();
    }
}
