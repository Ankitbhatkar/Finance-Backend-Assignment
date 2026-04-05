package com.example.finance.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    // Use a secure 32+ byte secret, Base64 encoded
    private final SecretKey SECRET_KEY;

    public JwtUtil() {
        // Example: generate a secure random key
        SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        // If you want a fixed key, you can use:
        // byte[] keyBytes = Base64.getDecoder().decode("YOUR_BASE64_ENCODED_SECRET");
        // SECRET_KEY = Keys.hmacShaKeyFor(keyBytes);
    }

    // Generate JWT token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract username from token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}