package com.qonsult.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleManagementDTO {
    private List<RoleDTO>roles;
    private List<GroupDTO>groups;
}
