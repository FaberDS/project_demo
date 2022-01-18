package com.example.task_demo.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Setter @Getter @NoArgsConstructor
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "handle", updatable = false, nullable = false)
    private UUID handle;

    @Column(nullable = false)
    private String title;

    public Project(String title){
        this.title = title;
        this.handle = UUID.randomUUID();
    }

}
