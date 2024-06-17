package com.qonsult.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionnairePassedDTO {
    private UUID questionnaireId;
    private String message = "questionnaire already passed";
    private Date completionDate;
    private boolean passed = true;
}
