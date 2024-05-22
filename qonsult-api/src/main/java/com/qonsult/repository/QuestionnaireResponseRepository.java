package com.qonsult.repository;

import com.qonsult.entity.QuestionnaireResponse;
import com.qonsult.generic.GenericRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionnaireResponseRepository extends GenericRepository<QuestionnaireResponse, UUID> {
}
