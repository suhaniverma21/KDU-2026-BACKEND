package com.assessment.quickShip.repository;

import com.assessment.quickShip.entity.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {

    private final Map<Long, User> userStore = new HashMap<>();
    private Integer idCounter = 1;

    // Save user entity
    public User save(User user) {
        user.setId(idCounter++);
        userStore.put(Long.valueOf(user.getId()), user);
        return user;
    }

    // Optional: find user by username (needed for JWT login)
    public Optional<User> findByUserName(String username) {
        return userStore.values().stream()
                .filter(u -> u.getUserName().equals(username))
                .findFirst();
    }
    public void deleteById(Long id) {
        userStore.remove(id);
    }
    public List<User> findAll() {
        return new ArrayList<>(userStore.values());
    }

}
