package com.qonsult.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionnaireViewDTO {
    private String name;
    private PatientInformationDTO patientInformations;
    private List<QuestionnaireViewTopicDTO> topics = new ArrayList<>();
    private String imageData;
}
