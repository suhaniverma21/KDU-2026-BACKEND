package com.springSecurity.hands_on.controller;

import com.springSecurity.hands_on.dto.LoginRequestDTO;
import com.springSecurity.hands_on.service.AuthService;
import org.springframework.web.bind.annotation.*;
import com.springSecurity.hands_on.dto.LoginResponseDTO;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO dto) {
        return authService.login(dto);
    }
}

