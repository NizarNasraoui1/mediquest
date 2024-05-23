package com.qonsult.dto;

import com.qonsult.entity.PatientInformation;
import com.qonsult.entity.QuestionAnswer;
import com.qonsult.entity.QuestionnaireRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionnaireResponseDTO {
    private UUID id;
    private PatientInformation patientInformation;
    private List<QuestionAnswer> questionAnswers;
    private Date signedDate;
    private String signature;
    private String filledBy;
    UUID questionnaireRequestId;
}
