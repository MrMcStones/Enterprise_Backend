package com.rasmus.enterprise_project.backend.controller;

import com.rasmus.enterprise_project.backend.model.TodoItem;
import com.rasmus.enterprise_project.backend.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<TodoItem>> getAllTodos() {
        List<TodoItem> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<TodoItem> createTodo(@RequestBody TodoItem todoItem) {
        TodoItem createdTodo = todoService.createTodo(todoItem);
        return ResponseEntity.ok(createdTodo);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<TodoItem> updateTodo(@PathVariable Long id, @RequestBody TodoItem updatedTodo) {
        TodoItem todo = todoService.updateTodo(id, updatedTodo);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}