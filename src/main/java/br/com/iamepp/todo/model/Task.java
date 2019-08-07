package br.com.iamepp.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description")
    @NotBlank
    private String description;

    @OneToMany(
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST},
            orphanRemoval = true,
            fetch = EAGER
    )
    private List<Subtask> subtasks = new ArrayList<>();

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    @NotNull
    private Status status = Status.TODO;

    public void complete() {
        this.status = Status.DONE;
    }

    public void addSubtask(Subtask subtask) {
        this.subtasks.add(subtask);
    }
}
