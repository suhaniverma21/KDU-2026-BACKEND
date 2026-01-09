package com.springSecurity.hands_on.Controller;

import com.springSecurity.hands_on.DTO.LoginRequestDTO;
import com.springSecurity.hands_on.DTO.LoginResponseDTO;
import com.springSecurity.hands_on.DTO.UserRequestDTO;
import com.springSecurity.hands_on.DTO.UserResponseDTO;
import com.springSecurity.hands_on.Service.UserService;
import com.springSecurity.hands_on.User;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/register")
    public UserResponseDTO  register(@RequestBody UserRequestDTO dto) {
        return userService.register(dto);
    }
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO dto) {
        return userService.login(dto);
    }
    @GetMapping("/me")
    public String me() {
        return "Hello, secured user!";
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users")
    public List<User> getUsers(Authentication authentication) {
        return userService.getAllUsers();
    }

}
