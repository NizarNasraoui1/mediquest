package com.qonsult.repository;

import com.qonsult.entity.QuestionAnswer;
import com.qonsult.generic.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionAnswerRepository extends GenericRepository<QuestionAnswer,Long> {
}
