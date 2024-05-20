package com.qonsult.init;

import com.qonsult.config.tenant_config.TenantContext;
import com.qonsult.config.tenant_config.TenantDataSource;
import com.qonsult.entity.Role;
import com.qonsult.entity.Schema;
import com.qonsult.entity.User;
import com.qonsult.repository.PermissionRepository;
import com.qonsult.repository.RoleRepository;
import com.qonsult.repository.SchemaRepository;
import com.qonsult.repository.UserRepository;
import com.qonsult.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Component
@RequiredArgsConstructor
public class InitCenterAdminUser{

    private final RoleRepository roleRepository;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;


    public void createAdminUser(String username) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(username));
        user.setEmailChecked(true);
        Role adminRole =  roleRepository.findAll().get(0);
        user.setRole(adminRole);
        userService.saveUser(user);

    }
}
