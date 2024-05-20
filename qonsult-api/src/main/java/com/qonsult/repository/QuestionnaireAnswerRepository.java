package com.qonsult.repository;

import com.qonsult.entity.QuestionnaireAnswer;
import com.qonsult.generic.GenericRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface QuestionnaireAnswerRepository extends GenericRepository<QuestionnaireAnswer,Long> {
    List<QuestionnaireAnswer> findAllByAppointmentDate(LocalDate appointmentDate);
}
