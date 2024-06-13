package com.qonsult.service.impl;

import com.qonsult.config.tenant_config.TenantSchemaResolver;
import com.qonsult.dto.*;
import com.qonsult.entity.auth.Account;
import com.qonsult.entity.auth.Group;
import com.qonsult.entity.auth.Role;
import com.qonsult.entity.auth.User;
import com.qonsult.exception.UsernameExistsException;
import com.qonsult.mapper.GroupMapper;
import com.qonsult.mapper.RoleMapper;
import com.qonsult.mapper.UserMapper;
import com.qonsult.service.AccountManagementService;
import com.qonsult.service.GroupService;
import com.qonsult.service.RoleService;
import com.qonsult.service.UserService;
import com.qonsult.util.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountManagementServiceImpl implements AccountManagementService {
    private final UserMapper userMapper;
    private final GroupMapper groupMapper;
    private final RoleMapper roleMapper;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TenantSchemaResolver tenantSchemaResolver;
    private final GroupService groupService;
    private final RoleService roleService;

    public List<UserDTO> getAllUsersBySchema(){
        String schemaName = tenantSchemaResolver.resolveCurrentTenantIdentifier();
        return userMapper.toDtos(userService.findAllBySchemaName(schemaName));
    }

    @Override
    public List<GroupDTO> getAccountGroups() {
        return groupMapper.toDtos(getCurrentAdmin().getGroup().getAccount().getGroups());
    }

    public UserDTO addUserToGroup(Long groupId,UserDTO userDTO) throws UsernameExistsException {
        if(userService.doesUserNameExists(userDTO.getUsername())){
            throw new UsernameExistsException("username already exists");
        }
        Group group = groupService.findById(groupId);
        User user = userMapper.toBo(userDTO);
        user.setGroup(group);
        String password = PasswordGenerator.generatePassword(12);
        log.info("should send email with temporary password "+password);
        user.setPassword(passwordEncoder.encode(password));
        return userMapper.toDto(userService.saveUser(user));
    }

    public GroupDTO addGroup(String groupName){
        Account adminAccount = getCurrentAdmin().getGroup().getAccount();
        Group group = new Group();
        group.setName(groupName);
        group.setAccount(adminAccount);
        return groupMapper.toDto(groupService.saveGroup(group));
    }

    public List<RoleDTO>getRolesByCurrentSchemaName(){
        String schemaName = tenantSchemaResolver.resolveCurrentTenantIdentifier();
        return roleMapper.toDtos(roleService.findAllBySchemaName(schemaName));
    }

    public List<GroupDTO>getGroupsByCurrentSchemaName(){
        String schemaName = tenantSchemaResolver.resolveCurrentTenantIdentifier();
        return groupMapper.toDtos(groupService.findAllBySchema(schemaName));
    }

    public RoleManagementDTO getRoleManagementByCurrentSchemaName(){
        List<RoleDTO>roles = getRolesByCurrentSchemaName();
        List<GroupDTO>groups = getGroupsByCurrentSchemaName();
        RoleManagementDTO roleManagementDTO = new RoleManagementDTO();
        roleManagementDTO.setRoles(roles);
        roleManagementDTO.setGroups(groups);
        return roleManagementDTO;
    }

    public void changeGroupRoles(List<ChangeRolesDTO> changeRolesDTOS){
        changeRolesDTOS.forEach((dto)->{
            Group group = groupService.findById(dto.getGroupId());
            List<Role> roles = roleService.getRolesByIds(dto.getRolesIds());
            group.setRoles(roles);
            groupService.saveGroup(group);
        });
    }

    private User getCurrentAdmin(){
        return userService.loadUserByUsername(userService.getCurrentUserUsername());
    }

    public UserDTO getAdminInformations(){
        return userMapper.toDto(getCurrentAdmin());
    }

    public void changePassword(ChangePasswordDTO changePasswordDTO) throws AuthenticationException {
        User user = getCurrentAdmin();
        if(!passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())){
            throw new AuthenticationException("wrong password");
        }
        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        userService.saveUser(user);
    }

    public UserDTO changeAdminInformations(UserDTO userDTO){
        User user = getCurrentAdmin();
        user.setTel(userDTO.getTel());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        User updatedUser = userService.saveUser(user);
        UserDTO updatedUserDto = userMapper.toDto(updatedUser);
        updatedUserDto.setPassword(null);
        return updatedUserDto;
    }

    @Override
    public UserDTO updateUser(Long id,UserDTO userDTO) {
        User user = userService.findByid(id);
        user.setUsername(user.getUsername());
        user.setTel(userDTO.getTel());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        if(user.getGroup().getId()!=userDTO.getGroup().getId()){
            Group group = groupService.findById(userDTO.getId());
            user.setGroup(group);
        }
        return userMapper.toDto(userService.saveUser(user));
    }

    @Override
    public GroupDTO updateGroup(Long id,String groupName) {
        Group group = groupService.findById(id);
        group.setName(groupName);
        return groupMapper.toDto(groupService.saveGroup(group));
    }

}
