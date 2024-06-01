package com.qonsult.entity.auth;

import javax.persistence.*;
import lombok.*;
import java.util.UUID;
import static javax.persistence.GenerationType.IDENTITY;


@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private boolean enabled = true;
    private boolean emailChecked = false;
    private boolean accountInitialized = false;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Group group;

}
