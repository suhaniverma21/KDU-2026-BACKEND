package com.assessment.quickShip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    public Integer id;
    private String userName;
    private String email;
    private List<String> roles;


}