package com.qonsult.service;

import com.qonsult.dto.*;
import com.qonsult.exception.UsernameExistsException;

import java.util.List;

public interface AccountManagementService {
    List<UserDTO> getAllUsersBySchema();
    UserDTO addUserToGroup(Long groupId,UserDTO userDTO) throws UsernameExistsException;
    GroupDTO addGroup(GroupDTO groupDTO);
    List<RoleDTO> getRolesByCurrentSchemaName();
    List<GroupDTO> getGroupsByCurrentSchemaName();
    RoleManagementDTO getRoleManagementByCurrentSchemaName();
    void changeGroupRoles(List<ChangeRolesDTO> changeRolesDTOS);
    UserDTO getAdminInformations();
    void changePassword(String password);
}
