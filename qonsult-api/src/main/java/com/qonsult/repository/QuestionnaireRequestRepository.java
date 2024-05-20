package com.qonsult.repository;

import com.qonsult.entity.QuestionnaireRequest;
import com.qonsult.generic.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionnaireRequestRepository extends GenericRepository<QuestionnaireRequest, Long> {
}
