package com.qonsult.entity;

import com.qonsult.enumeration.QuestionnaireRequestStateEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private UUID id;

    @Column(name = "appointment_date")
    private LocalDate appointmentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "questionnaire_request_state")
    private QuestionnaireRequestStateEnum questionnaireRequestState = QuestionnaireRequestStateEnum.SENT;

    @Column(name = "used_for_qr_code")
    private boolean usedForQrCode = false;

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private Date createdDate;

    @CreatedDate
    private Date passedDate;

    @ManyToOne
    @JoinColumn(name = "questionnaire_model_id")
    private QuestionnaireModel questionnaireModel;

    @OneToOne
    @JoinTable(
            name = "questionnaire_request_response",
            joinColumns = @JoinColumn(name = "questionnaire_request_id"),
            inverseJoinColumns = @JoinColumn(name = "questionnaire_response_id")
    )
    private QuestionnaireResponse questionnaireResponse;

}
