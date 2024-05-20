package com.qonsult.repository;

import com.qonsult.dto.ConditionDTO;
import com.qonsult.entity.Condition;
import com.qonsult.generic.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionRepository extends GenericRepository<Condition, Long> {
}
