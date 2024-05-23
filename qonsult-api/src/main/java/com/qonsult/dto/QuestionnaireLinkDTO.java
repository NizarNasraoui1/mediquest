package com.qonsult.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionnaireLinkDTO {
    private Long questionnaireModelId;
    private UUID questionnaireRequest;
    private String name;
}
