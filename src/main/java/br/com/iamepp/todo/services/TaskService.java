package br.com.iamepp.todo.services;

import br.com.iamepp.todo.model.Task;

import java.util.Optional;

public interface TaskService {
    Task save(Task task);

    Task complete(Task task);

    void remove(Task task);

    Optional<Task> findById(Long id);

    Iterable<Task> findAll();
}
