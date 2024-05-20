package com.qonsult.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name= "questionnaire_answer")
public class QuestionnaireAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate appointmentDate;
    @ManyToOne
    @JoinColumn(name="questionnaire_id")
    private Questionnaire questionnaire;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="patient_information_id")
    private PatientInformation patientInformation;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="question_answer")
    private List<QuestionAnswer> questionAnswers;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="certification_id",referencedColumnName = "id")
    private Certification certification;
    @Column(name = "created_date")
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate = new Date();

}
