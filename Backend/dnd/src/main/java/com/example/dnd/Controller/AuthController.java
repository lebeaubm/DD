// package com.example.dnd.Controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.dnd.service.UserService;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     @Autowired
//     private UserService userService;

//     @PostMapping("/signup")
//     public String signUp(@RequestParam String username, @RequestParam String password) {
//         userService.registerUser(username, password);
//         return "User registered successfully";
//     }

//     @PostMapping("/login")
//     public String login() {
//         // Spring Security handles the login process automatically
//         return "User logged in successfully";
//     }
//}