package br.com.iamepp.todo.services;

import br.com.iamepp.todo.model.Task;
import br.com.iamepp.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    private SubtaskService subtaskService;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, SubtaskService subtaskService) {
        this.taskRepository = taskRepository;
        this.subtaskService = subtaskService;
    }

    /**
     * Saves a given task
     *
     * @param task to be saved
     * @return the saved task
     */
    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Complete a given task and all it's subtasks
     *
     * @param task to be completed
     * @return the completed task
     */
    @Override
    public Task complete(Task task) {
        task.getSubtasks().forEach(s -> subtaskService.complete(s));
        task.complete();
        return taskRepository.save(task);
    }

    /**
     * Removes a given task
     *
     * @param task to be removed
     */
    @Override
    public void remove(Task task) {
        task.getSubtasks().forEach(s -> subtaskService.remove(s));
        taskRepository.delete(task);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }
}
