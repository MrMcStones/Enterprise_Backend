package com.rasmus.enterprise_project.backend.exception;

// Undantag som används när resurs inte kan hittas
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
