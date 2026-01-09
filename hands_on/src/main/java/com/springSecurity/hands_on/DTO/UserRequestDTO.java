package com.springSecurity.hands_on.DTO;

import lombok.Data;

@Data
public class UserRequestDTO {
    private String userName;
    private String password;
    private String email;
}
