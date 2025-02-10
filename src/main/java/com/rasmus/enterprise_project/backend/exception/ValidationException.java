package com.rasmus.enterprise_project.backend.exception;

// Undantag som används vid valideringsfel
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
