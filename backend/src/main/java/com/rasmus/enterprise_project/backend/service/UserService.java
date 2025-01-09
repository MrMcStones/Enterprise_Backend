package com.rasmus.enterprise_project.backend.service;


import com.rasmus.enterprise_project.backend.model.User;
import com.rasmus.enterprise_project.backend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String username, String rawPassword) {
        String hashedPassword = passwordEncoder.encode(rawPassword);
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }
}
