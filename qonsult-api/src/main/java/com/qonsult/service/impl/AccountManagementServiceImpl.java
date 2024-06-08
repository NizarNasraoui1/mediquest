package com.qonsult.service.impl;

import com.qonsult.config.tenant_config.TenantContext;
import com.qonsult.dto.*;
import com.qonsult.entity.auth.Group;
import com.qonsult.entity.auth.Role;
import com.qonsult.entity.auth.User;
import com.qonsult.mapper.GroupMapper;
import com.qonsult.mapper.RoleMapper;
import com.qonsult.mapper.UserMapper;
import com.qonsult.repository.GroupRepository;
import com.qonsult.repository.RoleRepository;
import com.qonsult.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountManagementServiceImpl {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final GroupMapper groupMapper;
    private final RoleMapper roleMapper;

    public List<UserDTO> getAllUsersBySchema(){
        String schemaName = TenantContext.getCurrentTenant();
        return userMapper.toDtos(userRepository.findAll(UserRepository.hasSchemaName(schemaName)));
    }

    public UserDTO addUserToGroup(AddUserGroupDTO addUserGroupDTO){
        Group group = groupRepository.findById(addUserGroupDTO.getGroupId()).orElseThrow(()->new EntityNotFoundException("group not found"));
        User user = new User();
        user.setUsername(addUserGroupDTO.getUserName());
        user.setFirstName(addUserGroupDTO.getFirstName());
        user.setLastName(addUserGroupDTO.getLastName());
        user.setEmail(addUserGroupDTO.getEmail());
        user.setTel(addUserGroupDTO.getTel());
        user.setGroup(group);
        return userMapper.toDto(user);
    }

    public GroupDTO addGroup(GroupDTO groupDTO){
        return groupMapper.toDto(groupRepository.save(groupMapper.toBo(groupDTO)));
    }

    public List<RoleDTO>getRolesByCurrentSchemaName(){
        String schemaName = TenantContext.getCurrentTenant();
        return roleMapper.toDtos(roleRepository.findAll(RoleRepository.hasSchemaName(schemaName)));
    }

    public List<GroupDTO>getGroupsByCurrentSchemaName(){
        String schemaName = TenantContext.getCurrentTenant();
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

}
