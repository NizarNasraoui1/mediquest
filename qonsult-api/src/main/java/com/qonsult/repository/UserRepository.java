package com.qonsult.repository;

import com.qonsult.entity.auth.Account;
import com.qonsult.entity.auth.Schema;
import com.qonsult.entity.auth.User;
import com.qonsult.generic.GenericRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.Join;

public interface UserRepository extends GenericRepository<User,Long> {
    User findByUsername(String username);

    User findByEmail(String userEmail);

    boolean existsByUsername(String username);

    static Specification<User> hasSchemaName(String schemaName) {
        return (root, query, criteriaBuilder) -> {
            Join<User, Schema> userAccountJoin = root.join("group").join("account").join("schema");
            return criteriaBuilder.equal(userAccountJoin.get("name"), schemaName);
        };
    }
}
