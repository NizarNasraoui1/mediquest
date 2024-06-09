package com.qonsult.service.impl;

import com.qonsult.config.tenant_config.TenantContext;
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
import com.qonsult.repository.GroupRepository;
import com.qonsult.repository.RoleRepository;
import com.qonsult.repository.UserRepository;
import com.qonsult.service.AccountManagementService;
import com.qonsult.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountManagementServiceImpl implements AccountManagementService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final GroupMapper groupMapper;
    private final RoleMapper roleMapper;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TenantSchemaResolver tenantSchemaResolver;

    public List<UserDTO> getAllUsersBySchema(){
        String schemaName = tenantSchemaResolver.resolveCurrentTenantIdentifier();
        return userMapper.toDtos(userRepository.findAll(UserRepository.hasSchemaName(schemaName)));
    }

    public UserDTO addUserToGroup(Long groupId,UserDTO userDTO) throws UsernameExistsException {
        if(userService.doesUserNameExists(userDTO.getUsername())){
            throw new UsernameExistsException("username already exists");
        }
        Group group = groupRepository.findById(groupId).orElseThrow(()->new EntityNotFoundException("group not found"));
        User user = userMapper.toBo(userDTO);
        user.setGroup(group);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }

    public GroupDTO addGroup(GroupDTO groupDTO){
        Account adminAccount = getCurrentAdmin().getGroup().getAccount();
        Group newGroup = groupMapper.toBo(groupDTO);
        newGroup.setAccount(adminAccount);
        return groupMapper.toDto(groupRepository.save(newGroup));
    }

    public List<RoleDTO>getRolesByCurrentSchemaName(){
        String schemaName = tenantSchemaResolver.resolveCurrentTenantIdentifier();
        return roleMapper.toDtos(roleRepository.findAll(RoleRepository.hasSchemaName(schemaName)));
    }

    public List<GroupDTO>getGroupsByCurrentSchemaName(){
        String schemaName = tenantSchemaResolver.resolveCurrentTenantIdentifier();
        return groupMapper.toDtos(groupRepository.findAll(GroupRepository.hasSchemaName(schemaName)));
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
            Group group = groupRepository.findById(dto.getGroupId()).orElseThrow(()-> new EntityNotFoundException("group not found"));
            List<Role>newRoles = new ArrayList<>();
            dto.getRolesIds().forEach((roleId)->{
                Role role = roleRepository.findById(roleId).orElseThrow(()-> new EntityNotFoundException("role not found"));
                newRoles.add(role);
            });
            group.setRoles(newRoles);
            groupRepository.save(group);
        });
    }

    private User getCurrentAdmin(){
        return userService.loadUserByUsername(userService.getCurrentUserUsername());
    }

    public UserDTO getAdminInformations(){
        return userMapper.toDto(getCurrentAdmin());
    }

    public void changePassword(String password){
        User user = getCurrentAdmin();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

}
