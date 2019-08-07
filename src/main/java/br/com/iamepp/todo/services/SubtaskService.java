package br.com.iamepp.todo.services;

import br.com.iamepp.todo.model.Subtask;

public interface SubtaskService {
    Subtask save(Subtask task);

    Subtask complete(Subtask task);

    void remove(Subtask task);
}
