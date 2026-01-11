package com.springSecurity.hands_on.entity;

import lombok.Data;

import java.util.List;
@Data
public class User {
    private Integer id;
    private String userName;
    private String password; // Must be stored securely!
    private String email;
    private List<String> roles; // e.g., "ROLE_BASIC", "ROLE_ADMIN"
}
