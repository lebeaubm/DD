// 
// //package com.example.dnd.Controller;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/api/users")
// public class UserController {

//     @GetMapping("/hello")
//     public String hello() {
//         return "Hello, D&D Social Media!";
//     }
// }

package com.example.dnd.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dnd.model.User;
import com.example.dnd.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        userService.registerUser(user.getUsername(), user.getEmail(), user.getPassword());
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = userService.signIn(username, password);
        if (user.isPresent()) {
            return ResponseEntity.ok("Sign-in successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}