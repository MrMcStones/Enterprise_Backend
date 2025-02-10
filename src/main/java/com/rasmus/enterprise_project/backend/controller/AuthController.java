package com.rasmus.enterprise_project.backend.controller;

import com.rasmus.enterprise_project.backend.security.JwtUtil;
import com.rasmus.enterprise_project.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.get("username"), credentials.get("password"))
        );

        UserDetails user = userService.loadUserByUsername(credentials.get("username"));
        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
