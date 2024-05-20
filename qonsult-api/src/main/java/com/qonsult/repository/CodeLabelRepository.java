package com.qonsult.repository;

import com.qonsult.entity.CodeLabel;
import com.qonsult.generic.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeLabelRepository extends GenericRepository<CodeLabel,Long> {
}
