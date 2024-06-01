package com.qonsult.repository;

import com.qonsult.entity.auth.Role;
import com.qonsult.generic.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends GenericRepository<Role,Long> {
    Role findByName(String name);
    List<Role> findAllByNameIn(List<String>roles);
}
