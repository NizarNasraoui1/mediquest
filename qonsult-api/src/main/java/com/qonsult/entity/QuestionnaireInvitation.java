//package com.qonsult.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//import java.util.List;
//
//@Entity
//@Table(name = "questionnaire_invitation")
//@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@Getter
//@Setter
//public class QuestionnaireInvitation {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @ElementCollection
//    private List<String> email;
//    private LocalDate appointmentDate;
//    @OneToOne
//    @JoinColumn(name="questionnaire_id")
//    QuestionnaireModel questionnaireModel;
//}
