package com.qonsult.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qonsult.entity.PatientInformation;
import com.qonsult.entity.QuestionAnswer;
import com.qonsult.entity.Questionnaire;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionnaireAnswerDTO {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate appointmentDate;
    private QuestionnaireDTO questionnaire;
    private PatientInformationDTO patientInformation;
    private List<QuestionAnswerDTO> questionAnswers;
    private CertificationDTO certification;
}
