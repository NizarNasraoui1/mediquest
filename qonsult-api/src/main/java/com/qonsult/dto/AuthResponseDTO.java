package com.qonsult.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthResponseDTO {
    private String accessToken;
    private String refreshToken;
    private String tenant;
    private String username;

}
