package com.qonsult.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddUserGroupDTO {
    private Long groupId;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String tel;
}
