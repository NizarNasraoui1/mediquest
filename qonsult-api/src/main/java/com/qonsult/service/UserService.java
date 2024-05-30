package com.qonsult.service;

import com.qonsult.entity.Group;
import com.qonsult.entity.Role;
import com.qonsult.entity.User;
import com.qonsult.exception.RoleAlreadyExistsException;
import org.bouncycastle.openssl.PasswordException;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.List;

public interface UserService {
    User saveUser(User user);
    Group saveRole(Group group) throws RoleAlreadyExistsException;

    void saveRoles(List<Group> groups);
    void addRoleToUser(Long roleId, Long authorityId);


    User getUser(String username);

    Role addPermission(String name);

    Group addPermissionToRole(Long roleId, Long authorityId);

    List<Role> getAllPermissions();

    List<User>getUsers();

    void saveCenterUser(User user);

    UserDetails loadUserByUsername(String username, String password) throws PasswordException;
}
