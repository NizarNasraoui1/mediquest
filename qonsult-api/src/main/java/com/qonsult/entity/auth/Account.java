package com.qonsult.entity.auth;

import javax.persistence.*;

import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String name;
    private String address;
    private String schemaName;
    @OneToMany(mappedBy = "account")
    List<Group>groups;

    @OneToOne
    @JoinColumn(name = "schema_id")
    Schema schema;
}
