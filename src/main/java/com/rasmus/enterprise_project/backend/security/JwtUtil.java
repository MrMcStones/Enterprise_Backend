package com.rasmus.enterprise_project.backend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "supersecretkey"; // Borde vara i en .env-fil

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 dag
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
