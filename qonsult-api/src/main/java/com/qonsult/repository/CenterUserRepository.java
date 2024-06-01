package com.qonsult.repository;

import com.qonsult.entity.auth.CenterUser;
import com.qonsult.generic.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterUserRepository extends GenericRepository<CenterUser,Long> {
    CenterUser findByUsername(String username);
}
