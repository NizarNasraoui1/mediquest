package com.qonsult.repository;

import com.qonsult.entity.Question;
import com.qonsult.generic.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends GenericRepository<Question,Long> {
}
