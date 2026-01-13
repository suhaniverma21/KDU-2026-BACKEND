package com.assessment.quickShip.service;

import com.assessment.quickShip.dto.LoginRequestDTO;
import com.assessment.quickShip.dto.LoginResponseDTO;
import com.assessment.quickShip.entity.User;
import com.assessment.quickShip.exceptions.UnauthorizedException;
import com.assessment.quickShip.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthService(PasswordEncoder encoder, JwtUtil jwtUtil, UserService userService) {
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {

        User user = userService.findByUsername(dto.getUserName());

        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Invalid credentials");
        }

        log.info("User '{}' logged in successfully", user.getUserName());

        String token = jwtUtil.generateToken(user.getUserName(), user.getRoles());
        return new LoginResponseDTO(token);
    }
}
