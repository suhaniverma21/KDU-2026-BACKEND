package com.springSecurity.hands_on.controller;

import com.springSecurity.hands_on.dto.UserRequestDTO;
import com.springSecurity.hands_on.dto.UserResponseDTO;
import com.springSecurity.hands_on.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('BASIC','ADMIN')")
    @GetMapping
    public List<UserResponseDTO> getUsers() {
        return userService.getAllUsers()
                .stream()
                .map(user -> {
                    UserResponseDTO dto = new UserResponseDTO();
                    dto.setId(user.getId());
                    dto.setUserName(user.getUserName());
                    dto.setEmail(user.getEmail());
                    dto.setRoles(user.getRoles());
                    return dto;
                })
                .toList();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public UserResponseDTO addUser(@RequestBody UserRequestDTO dto) {
        Logger logger = LoggerFactory.getLogger(UserController.class);


        UserResponseDTO createdUser = userService.addUser(dto);

        // Log the creation
        logger.info("User '{}' registered by ADMIN", createdUser.getUserName());

        return createdUser;
    }

}


