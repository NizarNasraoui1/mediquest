package com.qonsult.repository;

import com.qonsult.entity.auth.Group;
import com.qonsult.entity.auth.Schema;
import com.qonsult.entity.auth.User;
import com.qonsult.generic.GenericRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.Join;
import java.util.Optional;

public interface GroupRepository extends GenericRepository<Group, Long> {
    static Specification<Group> hasSchemaName(String schemaName) {
        return (root, query, criteriaBuilder) -> {
            Join<Group, Schema> userAccountJoin = root.join("account").join("schema");
            return criteriaBuilder.equal(userAccountJoin.get("name"), schemaName);
        };
    }
}
