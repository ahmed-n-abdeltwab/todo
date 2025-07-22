package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository repository;

    public TodoServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Todo> getAllTodos() {
        return repository.findAll();
    }

    @Override
    public Todo getTodoById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id " + id));
    }

    @Override
    public Todo createTodo(Todo todo) {
        todo.setId(null); // ensure new
        return repository.save(todo);
    }

    @Override
    public Todo updateTodo(Long id, Todo todo) {
        getTodoById(id);
        todo.setId(id);
        return repository.save(todo);
    }

    @Override
    public void deleteTodo(Long id) {
        getTodoById(id);
        repository.deleteById(id);
    }
}

