package com.qonsult.repository;

import com.qonsult.entity.Topic;
import com.qonsult.generic.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends GenericRepository<Topic,Long> {
}
