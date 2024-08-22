// package com.example.dnd.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// public class NoSecurityConfig {

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     // No-op security configuration
//     @Bean
//     public SecurityFilterChain noSecurityFilterChain(HttpSecurity http) throws Exception {
//         http.csrf(csrf -> csrf.disable())  // Disable CSRF protection
//             .authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().permitAll());  // Allow all requests

//         return http.build();
//     }
// }
