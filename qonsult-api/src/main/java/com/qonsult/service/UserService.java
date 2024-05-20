package com.qonsult.service;

import com.qonsult.entity.Permission;
import com.qonsult.entity.Role;
import com.qonsult.entity.User;
import com.qonsult.exception.RoleAlreadyExistsException;
import org.bouncycastle.openssl.PasswordException;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role) throws RoleAlreadyExistsException;

    void saveRoles(List<Role>roles);
    void addRoleToUser(Long roleId, Long authorityId);


    User getUser(String username);

    Permission addPermission(String name);

    Role addPermissionToRole(Long roleId, Long authorityId);

    List<Permission> getAllPermissions();

    List<User>getUsers();

    void saveCenterUser(User user);

    UserDetails loadUserByUsername(String username, String password) throws PasswordException;
}
