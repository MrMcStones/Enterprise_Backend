package com.rasmus.enterprise_project.backend.controller;


import com.rasmus.enterprise_project.backend.model.User;
import com.rasmus.enterprise_project.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.status(201).body(userService.registerUser(user.getUsername(), user.getPassword()));
    }
}
