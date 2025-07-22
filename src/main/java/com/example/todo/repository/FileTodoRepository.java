package com.example.todo.repository;

import com.example.todo.model.Todo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FileTodoRepository implements TodoRepository {

    private final File file = new File("todos.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private List<Todo> todos;

    public FileTodoRepository() {
        loadTodos();
    }

    private void loadTodos() {
        try {
            if (file.exists()) {
                todos = mapper.readValue(file, new TypeReference<List<Todo>>() {});
            } else {
                todos = new ArrayList<>();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load todos", e);
        }
    }

    private void saveTodos() {
        try {
            mapper.writeValue(file, todos);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save todos", e);
        }
    }

    @Override
    public List<Todo> findAll() {
        return new ArrayList<>(todos);
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return todos.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    @Override
    public Todo save(Todo todo) {
        if (todo.getId() == null) {
            todo.setId(nextId());
            todos.add(todo);
        } else {
            deleteById(todo.getId());
            todos.add(todo);
        }
        saveTodos();
        return todo;
    }

    @Override
    public void deleteById(Long id) {
        todos.removeIf(t -> t.getId().equals(id));
        saveTodos();
    }

    private Long nextId() {
        return todos.stream().mapToLong(Todo::getId).max().orElse(0) + 1;
    }
}
