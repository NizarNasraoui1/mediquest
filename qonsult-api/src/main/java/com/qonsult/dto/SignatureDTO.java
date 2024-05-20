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
public class SignatureDTO {
    private List<Double> clickX;
    private List<Double> clickY;
    private List<Boolean> clickDrag;
}
