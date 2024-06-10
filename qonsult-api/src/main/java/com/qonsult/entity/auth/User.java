package com.qonsult.entity.auth;

import javax.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.UUID;
import static javax.persistence.GenerationType.IDENTITY;


@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
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
    private String tel;
    private boolean enabled = true;
    private boolean emailChecked = false;
    private boolean accountInitialized = false;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private Group group;
    @CreatedDate
    Date createdDate;
    @LastModifiedDate
    Date modifiedDate;


}
