package com.rasmus.enterprise_project.backend;

import com.rasmus.enterprise_project.backend.model.TodoItem;
import com.rasmus.enterprise_project.backend.repository.TodoRepository;
import com.rasmus.enterprise_project.backend.service.TodoService;
import com.rasmus.enterprise_project.backend.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    private TodoItem testTodo;

    @BeforeEach
    void setUp() {
        testTodo = new TodoItem();
        testTodo.setId(1L);
        testTodo.setTitle("Test Todo");
        testTodo.setCompleted(false);
    }

    @Test
    void testCreateTodo() {
        when(todoRepository.save(any(TodoItem.class))).thenReturn(testTodo);

        TodoItem savedTodo = todoService.createTodo(testTodo);

        assertNotNull(savedTodo);
        assertEquals("Test Todo", savedTodo.getTitle());
        assertFalse(savedTodo.isCompleted());
        verify(todoRepository, times(1)).save(any(TodoItem.class));
    }

    @Test
    void testGetAllTodos() {
        when(todoRepository.findAll()).thenReturn(List.of(testTodo));

        List<TodoItem> todos = todoService.getAllTodos();

        assertNotNull(todos);
        assertEquals(1, todos.size());
        assertEquals("Test Todo", todos.get(0).getTitle());
        verify(todoRepository, times(1)).findAll();
    }

    @Test
    void testUpdateTodo() {
        when(todoRepository.findById(1L)).thenReturn(Optional.of(testTodo));
        when(todoRepository.save(any(TodoItem.class))).thenReturn(testTodo);

        testTodo.setTitle("Updated Title");
        testTodo.setCompleted(true);

        TodoItem updatedTodo = todoService.updateTodo(1L, testTodo);

        assertEquals("Updated Title", updatedTodo.getTitle());
        assertTrue(updatedTodo.isCompleted());
        verify(todoRepository, times(1)).findById(1L);
        verify(todoRepository, times(1)).save(any(TodoItem.class));
    }

    @Test
    void testUpdateTodo_NotFound() {
        when(todoRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            todoService.updateTodo(1L, testTodo);
        });

        assertEquals("Todo not found with id 1", exception.getMessage());
        verify(todoRepository, times(1)).findById(1L);
        verify(todoRepository, never()).save(any(TodoItem.class));
    }

    @Test
    void testDeleteTodo() {
        when(todoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(todoRepository).deleteById(1L);

        assertDoesNotThrow(() -> todoService.deleteTodo(1L));

        verify(todoRepository, times(1)).existsById(1L);
        verify(todoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteTodo_NotFound() {
        when(todoRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            todoService.deleteTodo(1L);
        });

        assertEquals("Todo not found with id 1", exception.getMessage());
        verify(todoRepository, times(1)).existsById(1L);
        verify(todoRepository, never()).deleteById(anyLong());
    }
}
