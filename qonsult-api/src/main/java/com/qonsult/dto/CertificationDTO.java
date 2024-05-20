package com.qonsult.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CertificationDTO {
    private Long id;
    private String filledBy;
    private boolean approved;
    private List<Double> clickX;
    private List<Double> clickY;
    private List<Boolean> clickDrag;
}
