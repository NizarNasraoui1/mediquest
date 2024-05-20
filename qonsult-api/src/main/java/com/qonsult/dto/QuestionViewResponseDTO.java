package com.qonsult.dto;

import com.qonsult.enumeration.QuestionTypeEnum;
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
public class QuestionViewResponseDTO {
    private QuestionTypeEnum questionType;
    private String question;
    private List<String>response = new ArrayList<>();
}
