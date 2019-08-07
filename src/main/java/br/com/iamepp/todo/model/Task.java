package br.com.iamepp.todo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

import static br.com.iamepp.todo.model.Status.DONE;
import static br.com.iamepp.todo.model.Status.TODO;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;

@Data
@Entity
public class Task {
    @Id
    @Column(name = "task_id")
    private Long id;

    @Column(name = "description")
    @NotBlank
    private String description;

    @OneToMany(
            cascade = {REMOVE, PERSIST},
            orphanRemoval = true,
            fetch = EAGER
    )
    private List<Subtask> subtasks;

    @Column(name = "status", nullable = false)
    @Enumerated(value = STRING)
    @NotNull
    private Status status = TODO;

    public void complete() {
        this.status = DONE;
    }

}
