package com.hands_on.JDBC.controller;

import com.hands_on.JDBC.model.User;
import com.hands_on.JDBC.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public String createUser(@RequestBody User user) {
        repo.save(user);
        return "User created successfully!";
    }

    @GetMapping("/tenants/{tenantId}")
    public List<User> getUsersByTenant(@PathVariable Long tenantId) {
        return repo.findAllByTenant(tenantId);
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        repo.update(user);
        return "User updated successfully!";
    }
    // PAGINATED + SORTED FETCH
    @GetMapping("/tenant/{tenantId}")
    public List<User> getUsersByTenant(
            @PathVariable Long tenantId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sort
    ) {
        return repo.findUsersByTenantWithPagination(
                tenantId,
                page,
                size,
                sort
        );
    }
}
