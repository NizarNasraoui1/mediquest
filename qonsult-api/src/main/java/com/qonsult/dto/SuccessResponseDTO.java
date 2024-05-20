package com.qonsult.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SuccessResponseDTO {
    private LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String message;
    private Object data;

    public SuccessResponseDTO(String message){
        this.message = message;
    }
}
