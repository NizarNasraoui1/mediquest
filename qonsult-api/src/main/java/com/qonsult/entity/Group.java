package com.qonsult.entity;

import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Getter @Setter
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="center_id")
    private Center center;

    @ManyToMany
    @JoinTable(
            name = "roles_permission",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "premission_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

    public Group(String roleName){
        this.name = roleName;
    }
}
