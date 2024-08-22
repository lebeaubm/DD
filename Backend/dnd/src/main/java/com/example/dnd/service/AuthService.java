// package com.example.dnd.service;

// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.example.dnd.model.User;
// import com.example.dnd.repository.UserRepository;
// import com.example.dnd.security.JwtUtil;

// @Service
// public class AuthService {

//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private PasswordEncoder passwordEncoder;  // Use PasswordEncoder interface

//     @Autowired
//     private JwtUtil jwtUtil;

//     public User registerUser(String username, String password) {
//         User user = new User();
//         user.setUsername(username);
//         user.setPassword(passwordEncoder.encode(password));  // Use PasswordEncoder for encoding
//         return userRepository.save(user);
//     }

//     public Optional<String> loginUser(String username, String password) {
//         Optional<User> user = userRepository.findByUsername(username);
//         if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
//             return Optional.of(jwtUtil.generateToken(username));
//         }
//         return Optional.empty();
//     }
// }
