package com.qonsult.service;

import com.qonsult.dto.*;
import com.qonsult.exception.UsernameExistsException;

import javax.naming.AuthenticationException;
import java.util.List;

public interface AccountManagementService {
    List<UserDTO> getAllUsersBySchema();

    List<GroupDTO> getAccountGroups();
    UserDTO addUserToGroup(Long groupId,UserDTO userDTO) throws UsernameExistsException;
    GroupDTO addGroup(String groupName);
    List<RoleDTO> getRolesByCurrentSchemaName();
    List<GroupDTO> getGroupsByCurrentSchemaName();
    RoleManagementDTO getRoleManagementByCurrentSchemaName();
    void changeGroupRoles(List<ChangeRolesDTO> changeRolesDTOS);
    UserDTO getAdminInformations();
    void changePassword(ChangePasswordDTO changePasswordDTO) throws AuthenticationException;

    UserDTO changeAdminInformations(UserDTO userDTO);

    UserDTO updateUser(Long id,UserDTO userDTO);

    GroupDTO updateGroup(Long id,String groupName);
}
