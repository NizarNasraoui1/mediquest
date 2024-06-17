package com.qonsult.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name= "questionnaire_response")
@EntityListeners(AuditingEntityListener.class)
public class QuestionnaireResponse {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "patient_information_id")
    private PatientInformation patientInformation;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_answer")
    private List<QuestionAnswer> questionAnswers;

    @Column(name = "signed_date", updatable = false)
    @CreatedDate
    private Date signedDate;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String signature;

    private String filledBy;

    @OneToOne
    @JoinTable(
            name = "questionnaire_request_response",
            joinColumns = @JoinColumn(name = "questionnaire_response_id"),
            inverseJoinColumns = @JoinColumn(name = "questionnaire_request_id")
    )
    private QuestionnaireRequest questionnaireRequest;
}
