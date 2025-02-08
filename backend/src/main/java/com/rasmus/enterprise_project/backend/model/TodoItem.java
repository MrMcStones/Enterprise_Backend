package com.rasmus.enterprise_project.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

// Representerar en 2Do databasen
@Entity
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 2Do ID

    @NotEmpty(message = "Title is required")
    private String title; // 2Do Titel

    private boolean completed; // 2Do Status

    // Getters och Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}