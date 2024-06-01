package com.qonsult.repository;

import com.qonsult.entity.auth.Schema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SchemaRepository extends JpaRepository<Schema, Long> {
}
