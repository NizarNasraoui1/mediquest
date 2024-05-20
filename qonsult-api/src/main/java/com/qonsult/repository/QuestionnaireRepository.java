package com.qonsult.repository;

import com.qonsult.entity.Questionnaire;
import com.qonsult.generic.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionnaireRepository extends GenericRepository<Questionnaire,Long> {
    Optional<Questionnaire> findByName(String name);
}
