package com.qonsult.dto;

import com.qonsult.enumeration.QuestionTypeEnum;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionDTO {
    private String id;
    private int rank;
    private String label;
    QuestionTypeEnum type;
    private ArrayList<CodeLabelDTO> content;
    private List<ConditionDTO> conditions;
}
