package com.assessment.quickShip.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class User {
    private Integer id;
    @NotNull
    private String userName;
    @NotBlank
    private String password; // Must be stored securely!
    private String email;
    private List<String> roles; // e.g., "ROLE_MANAGER", "ROLE_DRIVER"
}
