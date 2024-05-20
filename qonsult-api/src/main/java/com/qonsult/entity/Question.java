package com.qonsult.entity;

import com.qonsult.enumeration.QuestionTypeEnum;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rank;
    private String label;
    private QuestionTypeEnum type;
    @OneToMany(cascade = CascadeType.ALL)
    private List<CodeLabel> content = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<Condition> conditions = new ArrayList<>();

}
