package com.qonsult.entity;

import javax.persistence.*;

import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String name;
    private String address;
    private String schemaName;
    @OneToMany(mappedBy = "center")
    List<Group>groups;
}
