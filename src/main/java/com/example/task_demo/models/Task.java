package com.example.task_demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    @NotBlank
    private String title;
    @Column(nullable = false)
    private String description;

    @Min(0)
    private int timeEstimation;

    @Min(0)
    private int actualTimeMinutes;
    private LocalDateTime doneAt;
    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIgnore // if it should not be returned on a request
    private Project project;
}
