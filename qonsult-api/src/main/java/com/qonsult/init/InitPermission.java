package com.qonsult.init;

import com.qonsult.config.tenant_config.TenantContext;
import com.qonsult.entity.Permission;
import com.qonsult.entity.Role;
import com.qonsult.repository.PermissionRepository;
import com.qonsult.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("initPermission")
@RequiredArgsConstructor
public class InitPermission implements DBInitializer{

    private final PermissionRepository permissionRepository;

    private final RoleRepository roleRepository;

    @Override
    public boolean isAlreadyInitialized(){
        return !permissionRepository.findAll().isEmpty();
    }
    public void initPermission(){
        List<String>permissionNames = Arrays.asList("ADD_QUESTIONNAIRE","READ_QUESTIONNAIRE","ADD_USER");
        List<Permission>permissions = addPermission(permissionNames);
        permissionRepository.saveAll(permissions);
        Role role =  roleRepository.findAll().get(0);
        role.setPermissions(permissions);
            roleRepository.save(role);
    }

    public List<Permission> addPermission(List<String>permissionNames){
        List<Permission>permissions = new ArrayList<>();
        permissionNames.forEach(permissionName->{
            Permission permission = new Permission();
            permission.setName(permissionName);
            permissions.add(permission);
        });
        return permissions;
    }
    @Override
    public void init() {
        if(isAlreadyInitialized()){
            return;
        }
        initPermission();
    }
}
