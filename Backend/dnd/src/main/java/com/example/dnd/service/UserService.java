package com.example.dnd.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.dnd.model.User;
import com.example.dnd.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);  // In a real application, you should hash the password before saving
        return userRepository.save(user);
    }

    public Optional<User> signIn(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password));  // In a real application, use hashed passwords
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
