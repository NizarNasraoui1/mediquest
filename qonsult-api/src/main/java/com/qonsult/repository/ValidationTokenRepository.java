package com.qonsult.repository;

import com.qonsult.entity.auth.ValidationToken;
import com.qonsult.generic.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationTokenRepository extends GenericRepository<ValidationToken,Long> {
    ValidationToken findByToken(String token);
}
