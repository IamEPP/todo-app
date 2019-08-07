package br.com.iamepp.todo.services;

import br.com.iamepp.todo.model.Subtask;
import br.com.iamepp.todo.repository.SubtaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubtaskServiceImpl implements SubtaskService {

    private SubtaskRepository repository;

    @Autowired
    public SubtaskServiceImpl(SubtaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Subtask save(Subtask subtask) {
        return repository.save(subtask);
    }

    @Override
    public Subtask complete(Subtask subtask) {
        subtask.complete();
        return repository.save(subtask);
    }

    @Override
    public void remove(Subtask subtask) {
        repository.delete(subtask);
    }
}
