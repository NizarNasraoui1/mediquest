package com.qonsult.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="questionnaire_request")
public class QuestionnaireRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String firstName;
    private String lastName;
    private String email;
    @OneToOne
    @JoinColumn(name="questionnaire_answer_id")
    QuestionnaireAnswer questionnaireAnswer;
}
