package br.com.iamepp.todo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static br.com.iamepp.todo.model.Status.DONE;
import static br.com.iamepp.todo.model.Status.TODO;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
public class Subtask {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "subtask_id")
    private Long id;
    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;
    @NotNull
    @Enumerated(STRING)
    @Column(name = "status", nullable = false)
    private Status status = TODO;

    public void complete() {
        this.status = DONE;
    }
}
