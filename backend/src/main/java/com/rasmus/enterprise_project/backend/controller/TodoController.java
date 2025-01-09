package com.rasmus.enterprise_project.backend.controller;

import com.rasmus.enterprise_project.backend.exception.ValidationException;
import com.rasmus.enterprise_project.backend.model.TodoItem;
import com.rasmus.enterprise_project.backend.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller för att hantera API-anrop för 2Do's
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    // Konstruktor för att injicera service-lagret
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // Hämta alla 2Do's
    @GetMapping
    public ResponseEntity<List<TodoItem>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    // Skapa en ny 2Do
    @PostMapping
    public ResponseEntity<TodoItem> createTodo(@RequestBody TodoItem todoItem) {
        if (todoItem.getTitle() == null || todoItem.getTitle().isEmpty()) {
            throw new ValidationException("Title cannot be empty");
        }
        return ResponseEntity.status(201).body(todoService.createTodo(todoItem));
    }

    // Uppdatera en 2Do
    @PutMapping("/{id}")
    public ResponseEntity<TodoItem> updateTodo(
            @PathVariable Long id, @RequestBody TodoItem updatedTodo) {
        return ResponseEntity.ok(todoService.updateTodo(id, updatedTodo));
    }

    // Ta bort en 2Do
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}
