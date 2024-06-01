package com.qonsult.repository;

import com.qonsult.entity.auth.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByName(String name);
//    @Query("SELECT g FROM Group g JOIN FETCH g.permissions WHERE g.id = :id")
//    Group findByIdWithPermissions(@Param("id") Long id);
}
