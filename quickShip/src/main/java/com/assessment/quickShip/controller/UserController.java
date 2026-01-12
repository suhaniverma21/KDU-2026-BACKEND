package com.assessment.quickShip.controller;

import com.assessment.quickShip.dto.UserRequestDTO;
import com.assessment.quickShip.dto.UserResponseDTO;
import com.assessment.quickShip.service.UserService;
import jakarta.validation.Valid;
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

    //getting all users
    @PreAuthorize("hasAnyRole('DRIVER','MANAGER')")
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


    //adding user
    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping
    public UserResponseDTO addUser(@RequestBody @Valid UserRequestDTO dto) {
        Logger logger = LoggerFactory.getLogger(UserController.class);


        UserResponseDTO createdUser = userService.addUser(dto);

        // Log the creation
        logger.info("User '{}' registered by ADMIN", createdUser.getUserName());

        return createdUser;
    }

}

