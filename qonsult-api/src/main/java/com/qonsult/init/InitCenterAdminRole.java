package com.qonsult.init;

import com.qonsult.entity.Role;
import com.qonsult.exception.RoleAlreadyExistsException;
import com.qonsult.repository.RoleRepository;
import com.qonsult.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("initCenterAdminRole")
@RequiredArgsConstructor
public class InitCenterAdminRole implements DBInitializer {

    public final RoleRepository roleRepository;

    private final UserService userService;
    @Override
    public void init() throws RoleAlreadyExistsException {
        if(isAlreadyInitialized()){
            return ;
        }
        Role adminCenterRole=new Role();
        adminCenterRole.setName("CENTER_ADMIN");
        adminCenterRole.setPermissions(userService.getAllPermissions());
        userService.saveRole(adminCenterRole);
    }

    @Override
    public boolean isAlreadyInitialized() {
        return !roleRepository.findAll().isEmpty();
    }
}
