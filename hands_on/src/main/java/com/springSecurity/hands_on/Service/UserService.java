package com.springSecurity.hands_on.Service;

import com.springSecurity.hands_on.DTO.LoginRequestDTO;
import com.springSecurity.hands_on.DTO.LoginResponseDTO;
import com.springSecurity.hands_on.DTO.UserRequestDTO;
import com.springSecurity.hands_on.DTO.UserResponseDTO;
import com.springSecurity.hands_on.Repositories.UserRepository;
import com.springSecurity.hands_on.User;
import com.springSecurity.hands_on.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    // Register user
    public UserResponseDTO register(UserRequestDTO dto) {
        User user = new User();
        user.setUserName(dto.getUserName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setRoles(List.of("ROLE_USER"));

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    // Login user → generate JWT
    public LoginResponseDTO login(LoginRequestDTO dto) {
        User user = userRepository.findByUserName(dto.getUserName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        logger.info("User {} logged in successfully", dto.getUserName());
        // Generate JWT token
        String token = jwtUtil.generateToken(user.getUserName(), user.getRoles());

        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        return response;
    }

    // Helper: convert User to UserResponseDTO
    private UserResponseDTO mapToResponse(User user) {
        UserResponseDTO response = new UserResponseDTO();
        response.setId(user.getId());
        response.setUserName(user.getUserName());
        response.setEmail(user.getEmail());
        response.setRoles(user.getRoles());
        return response;
    }

    public void deleteUser(Long id) {
        //User existingBook = userRepository.findById(id);
        userRepository.deleteById(id);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
