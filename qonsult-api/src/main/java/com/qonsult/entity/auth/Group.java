package com.qonsult.entity.auth;

import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Getter @Setter
@Table(name = "UserGroup")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="center_id")
    private Account account;

    @ManyToMany
    @JoinTable(
            name = "roles_permission",
            joinColumns = @JoinColumn(
                    name = "group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

    public Group(String roleName){
        this.name = roleName;
    }
}
