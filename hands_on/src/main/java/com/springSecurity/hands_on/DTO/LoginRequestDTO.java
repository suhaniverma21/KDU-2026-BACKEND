package com.springSecurity.hands_on.DTO;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String userName;
    private String password;
}
