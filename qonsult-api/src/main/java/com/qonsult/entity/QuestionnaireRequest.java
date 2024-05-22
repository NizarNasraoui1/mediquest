package com.qonsult.entity;

import com.qonsult.enumeration.QuestionnaireRequestStateEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="questionnaire_request")
public class QuestionnaireRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private LocalDate appointmentDate;
    @Enumerated(EnumType.STRING)
    private QuestionnaireRequestStateEnum questionnaireRequestState = QuestionnaireRequestStateEnum.SENT;
    @Column(name="used_for_qr_code")
    private boolean usedForQrCode = false;
    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private Date createdDate;
    @ManyToOne
    @JoinColumn(name="questionnaire_model_id")
    QuestionnaireModel questionnaireModel;
    @OneToOne
    @JoinTable(name = "questionnaire_request_response")
    QuestionnaireResponse questionnaireResponse;

}
