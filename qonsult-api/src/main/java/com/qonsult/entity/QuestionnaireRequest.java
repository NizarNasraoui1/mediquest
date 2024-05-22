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
@EntityListeners(AuditingEntityListener.class)
public class QuestionnaireRequest {
    @Id
    @GeneratedValue(generator = "UUID")
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
    @JoinTable(name = "questionnaire_request_response",joinColumns = @JoinColumn(name="questionnaire_request_id"),inverseJoinColumns = @JoinColumn(name = "questionnaire_response_id"))
    QuestionnaireResponse questionnaireResponse;

}
