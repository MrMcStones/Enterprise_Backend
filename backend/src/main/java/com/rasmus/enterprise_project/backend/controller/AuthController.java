package com.rasmus.enterprise_project.backend.controller;

import com.rasmus.enterprise_project.backend.model.User;
import com.rasmus.enterprise_project.backend.security.JwtUtil;
import com.rasmus.enterprise_project.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.registerUser(user.getUsername(), user.getPassword());
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        Optional<User> foundUser = userService.findByUsername(user.getUsername());

        if (foundUser.isPresent() && userService.verifyPassword(user.getPassword(), foundUser.get().getPassword())) {
            String token = JwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(token);
        }

        return ResponseEntity.status(401).body("Invalid username or password");
    }
}
