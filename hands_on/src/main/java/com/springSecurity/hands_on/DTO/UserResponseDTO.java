package com.springSecurity.hands_on.DTO;

import lombok.Data;

import java.util.List;
@Data
public class UserResponseDTO {
    public Integer id;
    private String userName;
    private String email;
    private List<String> roles;


}
