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
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "account")
    private List<Group> groups;

    @OneToOne
    @JoinColumn(name = "schema_id")
    private Schema schema;
}
