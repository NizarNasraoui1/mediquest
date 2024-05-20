package com.qonsult.repository;

import com.qonsult.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
    @Query("SELECT r FROM Role r JOIN FETCH r.permissions WHERE r.id = :id")
    Role findByIdWithPermissions(@Param("id") Long id);
}
