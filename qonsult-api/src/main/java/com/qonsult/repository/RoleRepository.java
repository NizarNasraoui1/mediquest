package com.qonsult.repository;

import com.qonsult.entity.auth.Group;
import com.qonsult.entity.auth.Role;
import com.qonsult.entity.auth.Schema;
import com.qonsult.generic.GenericRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Join;
import java.util.List;

@Repository
public interface RoleRepository extends GenericRepository<Role,Long> {
    static Specification<Role> hasSchemaName(String schemaName) {
        return (root, query, criteriaBuilder) -> {
            Join<Role, Schema> userAccountJoin = root.join("groups").join("account").join("schema");
            return criteriaBuilder.equal(userAccountJoin.get("name"), schemaName);
        };
    }
}
