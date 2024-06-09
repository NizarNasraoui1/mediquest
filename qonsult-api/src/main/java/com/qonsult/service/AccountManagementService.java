package com.qonsult.service;

import com.qonsult.dto.*;

import java.util.List;

public interface AccountManagementService {
    List<UserDTO> getAllUsersBySchema();
    UserDTO addUserToGroup(Long groupId,UserDTO userDTO);
    GroupDTO addGroup(GroupDTO groupDTO);
    List<RoleDTO> getRolesByCurrentSchemaName();
    List<GroupDTO> getGroupsByCurrentSchemaName();
    RoleManagementDTO getRoleManagementByCurrentSchemaName();
    void changeGroupRoles(List<ChangeRolesDTO> changeRolesDTOS);
    UserDTO getAdminInformations();
    void changePassword(String password);
}
