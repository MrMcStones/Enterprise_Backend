package com.rasmus.enterprise_project.backend.repository;


import com.rasmus.enterprise_project.backend.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

// Gränssnitt som hanterar operationer i databas för 2Do's
public interface TodoRepository extends JpaRepository<TodoItem, Long> {
}
