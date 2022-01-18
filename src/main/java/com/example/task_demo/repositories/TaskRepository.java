package com.example.task_demo.repositories;

import com.example.task_demo.models.Project;
import com.example.task_demo.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByProject(Project project);

    //Variante Methodenname
    //List<Task> findAllByProjectAndDoneAtIsNotNull(Project project);
    //List<Task> findAllByProjectAndDoneAtIsNull(Project project);
    //Variante SQL-Statement
    //?1 erster Parameter
    @Query("SELECT t FROM Task t WHERE t.project = ?1 AND t.doneAt IS Null")
    List<Task> findAllOpenTasksByProject(Project project);
    @Query("SELECT t FROM Task t WHERE t.project = ?1 AND t.doneAt IS NOT Null")
    List<Task> findAllDoneTasksByProject(Project project);

}
