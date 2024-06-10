package com.qonsult.service;

import com.qonsult.entity.auth.Role;

import java.util.List;

public interface RoleService {
    Role findById(Long id);
    List<Role>findAllBySchemaName(String schemaName);
}
