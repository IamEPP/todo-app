package br.com.iamepp.todo.repository;

import br.com.iamepp.todo.model.Subtask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtaskRepository extends CrudRepository<Subtask, Long> {
}
