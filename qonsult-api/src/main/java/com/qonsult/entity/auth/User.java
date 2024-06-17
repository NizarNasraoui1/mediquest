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
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "uuid")
        private UUID uuid;

        @Column(name = "first_name")
        private String firstName;

        @Column(name = "last_name")
        private String lastName;

        @Column(name = "username")
        private String username;

        @Column(name = "password")
        private String password;

        @Column(name = "email")
        private String email;

        @Column(name = "tel")
        private String tel;

        @Column(name = "enabled")
        private boolean enabled = true;

        @Column(name = "email_checked")
        private boolean emailChecked = false;

        @Column(name = "account_initialized")
        private boolean accountInitialized = false;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "group_id")
        private Group group;

        @CreatedDate
        @Column(name = "created_date")
        private Date createdDate;

        @LastModifiedDate
        @Column(name = "modified_date")
        private Date modifiedDate;


}
