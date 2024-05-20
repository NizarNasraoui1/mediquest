package com.qonsult.init;

import com.qonsult.entity.Role;
import com.qonsult.exception.RoleAlreadyExistsException;
import com.qonsult.repository.RoleRepository;
import com.qonsult.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("initSuperAdminRole")
@RequiredArgsConstructor
public class InitRoles implements DBInitializer {

    public final RoleRepository roleRepository;

    private final UserService userService;
    @Override
    public void init() throws RoleAlreadyExistsException {
        if(isAlreadyInitialized()){
            return ;
        }
        ArrayList<Role>roles = new ArrayList<>();
        roles.add(new Role("ADMIN"));
        roles.add(new Role("CENTER_ADMIN"));
        userService.saveRoles(roles);
    }

    @Override
    public boolean isAlreadyInitialized() {
        return !roleRepository.findAll().isEmpty();
    }
}
