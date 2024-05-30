package com.qonsult.init;

import com.qonsult.entity.Group;
import com.qonsult.entity.Role;
import com.qonsult.repository.RoleRepository;
import com.qonsult.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("initRoles")
@RequiredArgsConstructor
public class InitRoles implements DBInitializer{

    private final RoleRepository roleRepository;

    private final GroupRepository groupRepository;

    @Override
    public boolean isAlreadyInitialized(){
        return !roleRepository.findAll().isEmpty();
    }
    public void initRoles(){
        List<String>rolesNames = Arrays.asList("ADD_QUESTIONNAIRE","READ_QUESTIONNAIRE","REMOVE_QUESTIONNAIRE");
        List<Role> roles = getRolesFromNames(rolesNames);
        roleRepository.saveAll(roles);
    }

    public List<Role> getRolesFromNames(List<String>rolesNames){
        List<Role> roles = new ArrayList<>();
        rolesNames.forEach(permissionName->{
            Role role = new Role();
            role.setName(permissionName);
            roles.add(role);
        });
        return roles;
    }
    @Override
    public void init() {
        if(isAlreadyInitialized()){
            return;
        }
        initRoles();
    }
}
