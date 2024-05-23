package com.qonsult.repository;

import com.qonsult.entity.QuestionnaireModel;
import com.qonsult.generic.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionnaireModelRepository extends GenericRepository<QuestionnaireModel,Long> {
    Optional<QuestionnaireModel> findByName(String name);
}
