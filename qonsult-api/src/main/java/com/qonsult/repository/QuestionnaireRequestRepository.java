package com.qonsult.repository;

import com.qonsult.entity.QuestionnaireRequest;
import com.qonsult.generic.GenericRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionnaireRequestRepository extends GenericRepository<QuestionnaireRequest, UUID> {
    List<QuestionnaireRequest> findAllByAppointmentDate(LocalDate localDate);
    List<QuestionnaireRequest> findAllByUsedForQrCodeTrue();
}
