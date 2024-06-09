package com.qonsult.resource;

import com.qonsult.dto.*;
import com.qonsult.service.AccountManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account-management")
@RequiredArgsConstructor
public class AccountManagementResource {
    private final AccountManagementService accountManagementService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsersBySchema() {
        return ResponseEntity.ok(accountManagementService.getAllUsersBySchema());
    }

    @PostMapping("/groups/{groupId}/users")
    public ResponseEntity<UserDTO> addUserToGroup(@PathVariable Long groupId, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(accountManagementService.addUserToGroup(groupId, userDTO));
    }
    @PostMapping("/groups")
    public ResponseEntity<GroupDTO> addGroup(@RequestBody GroupDTO groupDTO) {
        return ResponseEntity.ok(accountManagementService.addGroup(groupDTO));
    }

    @GetMapping("/role-management")
    public ResponseEntity<RoleManagementDTO> getRoleManagementByCurrentSchemaName() {
        return ResponseEntity.ok(accountManagementService.getRoleManagementByCurrentSchemaName());
    }

    @PutMapping("/group-roles")
    public ResponseEntity<Void> changeGroupRoles(@RequestBody List<ChangeRolesDTO> changeRolesDTOS) {
        accountManagementService.changeGroupRoles(changeRolesDTOS);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin-info")
    public ResponseEntity<UserDTO> getAdminInformation() {
        return ResponseEntity.ok(accountManagementService.getAdminInformations());
    }

    @PutMapping("/admin-password")
    public ResponseEntity<Void> changePassword(@RequestBody String password) {
        accountManagementService.changePassword(password);
        return ResponseEntity.ok().build();
    }
}
