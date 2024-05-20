package com.qonsult.repository;

import com.qonsult.entity.Permission;
import com.qonsult.generic.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRepository extends GenericRepository<Permission,Long> {
    Permission findByName(String name);
    List<Permission> findAllByNameIn(List<String>permissions);
}
