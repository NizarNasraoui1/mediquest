package com.qonsult.repository;

import com.qonsult.entity.Center;
import com.qonsult.generic.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CenterRepository extends GenericRepository<Center,Long> {
    Optional<Center> findByName(String name);
}
