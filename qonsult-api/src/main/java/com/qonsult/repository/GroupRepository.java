package com.qonsult.repository;

import com.qonsult.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByName(String name);
//    @Query("SELECT g FROM Group g JOIN FETCH g.permissions WHERE g.id = :id")
//    Group findByIdWithPermissions(@Param("id") Long id);
}
