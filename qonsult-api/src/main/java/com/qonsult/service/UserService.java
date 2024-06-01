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

    List<User>getUsers();

    User loadUserByUsername(String username, String password);
}
