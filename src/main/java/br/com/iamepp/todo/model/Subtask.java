package br.com.iamepp.todo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Subtask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subtask_id")
    private Long id;
    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.TODO;

    public void complete() {
        this.status = Status.DONE;
    }
}
