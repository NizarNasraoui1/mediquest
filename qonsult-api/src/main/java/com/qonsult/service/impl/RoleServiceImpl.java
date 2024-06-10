package com.qonsult.service.impl;

import com.qonsult.entity.auth.Role;
import com.qonsult.repository.RoleRepository;
import com.qonsult.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("role not found"));
    }

    @Override
    public List<Role> findAllBySchemaName(String schemaName) {
        return roleRepository.findAll(RoleRepository.hasSchemaName(schemaName));
    }
}
