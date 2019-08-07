package br.com.iamepp.todo.api;

import br.com.iamepp.todo.model.Subtask;
import br.com.iamepp.todo.model.Task;
import br.com.iamepp.todo.services.SubtaskService;
import br.com.iamepp.todo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskResource {
    private TaskService taskService;
    private SubtaskService subtaskService;

    @Autowired
    public TaskResource(TaskService taskService, SubtaskService subtaskService) {
        this.taskService = taskService;
        this.subtaskService = subtaskService;
    }

    @GetMapping
    public List<Task> allTasks() {
        return Collections.emptyList();
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody @Valid Task task, BindingResult bindingResult, UriComponentsBuilder builder) {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || (task == null)) {
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        Task savedTask = taskService.save(task);
        headers.setLocation(builder.path("/api/tasks/{id}").buildAndExpand(task.getId()).toUri());
        return new ResponseEntity<>(savedTask, headers, HttpStatus.CREATED);
    }

    @PostMapping("/{parentId}/subtasks")
    public ResponseEntity<Subtask> addSubTask(@PathVariable("parentId") Long taskId,
                                              @RequestBody @Valid Subtask subtask,
                                              BindingResult bindingResult,
                                              UriComponentsBuilder builder) {

        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        Optional<Task> task = taskService.findById(taskId);
        if (bindingResult.hasErrors() || (task.isEmpty())) {
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        Subtask saved = subtaskService.save(subtask);
        URI location = builder.path("/api/tasks/{taskId}/{subtaskId}").buildAndExpand(taskId, saved.getId()).toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(subtask, headers, HttpStatus.CREATED);
    }
}
