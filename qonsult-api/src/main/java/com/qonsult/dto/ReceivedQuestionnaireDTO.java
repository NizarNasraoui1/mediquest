package com.qonsult.dto;
import com.qonsult.enumeration.QuestionnaireRequestStateEnum;
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
public class ReceivedQuestionnaireDTO {
    String name;
    Date receptionDate;
    UUID questionnaireResponseId;
    QuestionnaireRequestStateEnum questionnaireRequestState;
}
