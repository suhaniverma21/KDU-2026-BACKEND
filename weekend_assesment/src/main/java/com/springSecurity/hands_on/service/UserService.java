package com.springSecurity.hands_on.service;

import com.springSecurity.hands_on.dto.*;
import com.springSecurity.hands_on.exceptions.InvalidCredentialsException;
import com.springSecurity.hands_on.exceptions.UnauthorizedException;
import com.springSecurity.hands_on.exceptions.UserNotFoundException;
import com.springSecurity.hands_on.Repositories.UserRepository;
import com.springSecurity.hands_on.SecurityConstants;
import com.springSecurity.hands_on.entity.User;
import com.springSecurity.hands_on.security.JwtUtil;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    private static final Logger logger =
            LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository,
                       JwtUtil jwtUtil,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }
    @PostConstruct
    public void initAdmin() {
        if (userRepository.findByUserName("librarian").isEmpty()) {
            User librarian = new User();
            librarian.setUserName("librarian");
            librarian.setPassword(passwordEncoder.encode("librarian123"));
            librarian.setEmail("librarian@test.com");
            librarian.setRoles(List.of(SecurityConstants.ROLE_LIBRARIAN));

            userRepository.save(librarian);
            logger.info("Default LIBRARIAN user created");
        }
    }


    // Register user (BASIC)
    public UserResponseDTO register(UserRequestDTO dto) {

        User user = new User();
        user.setUserName(dto.getUserName());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); //
        user.setEmail(dto.getEmail());
        user.setRoles(List.of(SecurityConstants.ROLE_MEMBER));

        User savedUser = userRepository.save(user);
        return mapToResponse(savedUser);
    }

    // Login → generate JWT
    public LoginResponseDTO login(LoginRequestDTO dto) {

        User user = userRepository.findByUserName(dto.getUserName())
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        logger.info("User {} logged in successfully", dto.getUserName());

        String token = jwtUtil.generateToken(
                user.getUserName(),
                user.getRoles()
        );

        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        return response;
    }

    // Add new user (ADMIN only)
    public UserResponseDTO addUser(UserRequestDTO dto) {

        User user = new User();
        user.setUserName(dto.getUserName());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); //ENCODED
        user.setEmail(dto.getEmail());
        user.setRoles(List.of(SecurityConstants.ROLE_MEMBER));

        User savedUser = userRepository.save(user);
        return mapToResponse(savedUser);
    }

    public User findByUsername(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(() ->
                        new UnauthorizedException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    private UserResponseDTO mapToResponse(User user) {

        UserResponseDTO response = new UserResponseDTO();
        response.setId(user.getId());
        response.setUserName(user.getUserName());
        response.setEmail(user.getEmail());
        response.setRoles(user.getRoles());

        return response;
    }
}
