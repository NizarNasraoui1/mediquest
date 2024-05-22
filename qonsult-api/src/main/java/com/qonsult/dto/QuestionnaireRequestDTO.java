package com.qonsult.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qonsult.entity.QuestionnaireModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionnaireRequestDTO {
    private List<String> emails;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate appointmentDate;
    QuestionnaireModelDTO questionnaireModel;
}
