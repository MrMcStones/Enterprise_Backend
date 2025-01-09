package com.rasmus.enterprise_project.backend.repository;


import com.rasmus.enterprise_project.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
