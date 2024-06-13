package com.qonsult.service.impl;

import com.qonsult.config.tenant_config.TenantDataSource;
import com.qonsult.entity.auth.User;
import com.qonsult.repository.*;
import com.qonsult.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public String getCurrentUserUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return null;
    }

    public void checkMailConfirmed(User user) throws Exception {
        if(!user.isEmailChecked()){
            throw new Exception("mail not checked");
        }
    }

    @Override
    public User findByid(Long id) {
        return userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("user not found"));
    }

    @Override
    public List<User> findAllBySchemaName(String schemaName) {
        return userRepository.findAll(UserRepository.hasSchemaName(schemaName));
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getLastName());
        return userRepository.save(user);

    }
    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    public boolean doesUserNameExists(String username){
        return userRepository.existsByUsername(username);
    }
}
