package com.assessment.quickShip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private String userName;
    private String password;
    private String email;
    private List<String> roles;
}