// package com.example.dnd.security;

// import java.util.Date;

// import org.springframework.stereotype.Component;

// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;

// @Component
// public class JwtUtil {

//     private String secret = "dndSecretKey"; // Use a strong key and store securely

//     public String generateToken(String username) {
//         return Jwts.builder()
//                 .setSubject(username)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
//                 .signWith(SignatureAlgorithm.HS256, secret)
//                 .compact();
//     }

//     public String extractUsername(String token) {
//         return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
//     }

//     public boolean validateToken(String token, String username) {
//         String extractedUsername = extractUsername(token);
//         return (extractedUsername.equals(username) && !isTokenExpired(token));
//     }

//     private boolean isTokenExpired(String token) {
//         return extractExpiration(token).before(new Date());
//     }

//     private Date extractExpiration(String token) {
//         return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration();
//     }
// }