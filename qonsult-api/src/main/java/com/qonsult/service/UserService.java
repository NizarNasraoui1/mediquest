package com.qonsult.service;

import com.qonsult.entity.auth.User;


import java.util.List;

public interface UserService {

    User findByid(Long id);

    List<User>findAllBySchemaName(String name);
    User saveUser(User user);

    List<User>getUsers();

    User loadUserByUsername(String username);

    String getCurrentUserUsername();

    boolean doesUserNameExists(String username);
}
