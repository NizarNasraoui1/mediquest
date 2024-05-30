package com.qonsult.service.impl;

import com.qonsult.config.tenant_config.TenantContext;
import com.qonsult.config.tenant_config.TenantDataSource;
import com.qonsult.entity.*;
import com.qonsult.exception.RoleAlreadyExistsException;
import com.qonsult.repository.*;
import com.qonsult.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.openssl.PasswordException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final RoleRepository roleRepository;
    private final SchemaRepository schemaRepository;
    private final TenantDataSource tenantDataSource;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username,String password) throws UsernameNotFoundException,PasswordException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            log.error("User " +username+" not found in the database");
            throw new UsernameNotFoundException("bad credentials");
        }
        if(!passwordEncoder.matches(password,user.getPassword())){
            log.error("password for user "+user.getUsername().toString()+" is wrong");
            throw new PasswordException("bad credentials");
        }
        else {
            try {
                checkMailConfirmed(user);
            } catch (Exception e) {
                throw new RuntimeException("mail not confirmed");
            }
            log.info("User found in the database: {}", username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//            Group group = groupRepository.findByIdWithPermissions(user.getGroup().getId());
//            group.getRoles().forEach(permission -> {
//                authorities.add(new SimpleGrantedAuthority(permission.getName()));
//            });
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
    }

    public void checkMailConfirmed(User user) throws Exception {
        if(!user.isEmailChecked()){
            throw new Exception("mail not checked");
        }
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getLastName());
        return userRepository.save(user);

    }

    public void saveCenterUser(User user){
        Group adminGroup =  groupRepository.findAll().get(0);
        user.setGroup(adminGroup);
        try{
            saveUser(user);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Schema schema = new Schema();
        schema.setUserName(user.getUsername());
        schema.setName(TenantContext.getCurrentTenant());
        schemaRepository.save(schema);
        tenantDataSource.getUsernameSchemaMap().put(user.getUsername(),TenantContext.getCurrentTenant());
    }

    @Override
    public Group saveRole(Group group) throws RoleAlreadyExistsException {
        if(!groupRepository.findByName(group.getName()).isPresent()){
            log.info("Saving new role {} to the database", group.getName());
            return groupRepository.save(group);
        }
        throw new RoleAlreadyExistsException();

    }
    @Override
    public void saveRoles(List<Group> groups) {
        groups.forEach((role)->{
            try {
                this.saveRole(role);
            } catch (RoleAlreadyExistsException e) {
                throw new RuntimeException(e);
            }
        });


    }


    @Override
    public void addRoleToUser(Long roleId, Long authorityId) {

    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public Role addPermission(String name) {
        Role role = new Role();
        role.setName(name);
        return roleRepository.save(role);
    }

    @Override
    public Group addPermissionToRole(Long roleId, Long authorityId) {
        return null;
    }

    @Override
    public List<Role> getAllPermissions() {
        return roleRepository.findAll();
    }


    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }
}
