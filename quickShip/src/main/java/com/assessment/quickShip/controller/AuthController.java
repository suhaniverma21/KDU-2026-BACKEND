package com.assessment.quickShip.controller;

import com.assessment.quickShip.dto.LoginRequestDTO;
import com.assessment.quickShip.dto.LoginResponseDTO;
import com.assessment.quickShip.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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