//package com.qonsult.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.List;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Getter
//@Setter
//public class Certification {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String filledBy;
//    private boolean approved;
//    @ElementCollection
//    private List<Double> clickX;
//    @ElementCollection
//    private List<Double> clickY;
//    @ElementCollection
//    private List<Boolean> clickDrag;
//}
