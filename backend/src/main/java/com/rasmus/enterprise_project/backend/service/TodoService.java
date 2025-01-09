package com.rasmus.enterprise_project.backend.service;


import com.rasmus.enterprise_project.backend.exception.ResourceNotFoundException;
import com.rasmus.enterprise_project.backend.model.TodoItem;
import com.rasmus.enterprise_project.backend.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    // Konstruktor för att injicera repository-lagret
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // Hämta alla 2Do's från databasen
    public List<TodoItem> getAllTodos() {
        return todoRepository.findAll();
    }

    // Skapa en ny 2Do
    public TodoItem createTodo(TodoItem todoItem) {
        return todoRepository.save(todoItem);
    }

    // Uppdatera en 2Do
    public TodoItem updateTodo(Long id, TodoItem updatedTodo) {
        return todoRepository.findById(id)
                .map(existingTodo -> {
                    existingTodo.setTitle(updatedTodo.getTitle());
                    existingTodo.setCompleted(updatedTodo.isCompleted());
                    return todoRepository.save(existingTodo);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id " + id));
    }

    // Ta bort en 2Do
    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Todo not found with id " + id);
        }
        todoRepository.deleteById(id);
    }
}
