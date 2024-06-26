package com.qonsult.dto;

import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class QuestionnaireModelDTO {
    private Long id;
    private String name;
    private List<TopicDTO> topics;
}
