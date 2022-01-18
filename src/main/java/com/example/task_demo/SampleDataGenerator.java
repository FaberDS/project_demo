package com.example.task_demo;

import com.example.task_demo.models.Project;
import com.example.task_demo.models.Task;
import com.example.task_demo.repositories.ProjectRepository;
import com.example.task_demo.repositories.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class SampleDataGenerator implements CommandLineRunner {

    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;


    public SampleDataGenerator(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        List<Project> projects = generateSampleProjects(10);
        generateSampleTasks(15);
    }

    private List<Project> generateSampleProjects(int numberOfProjects) {
        List<Project> projects = new ArrayList<>();
        for(int i=1 ; i<=numberOfProjects; i++){
            projects.add(new Project("Project-" +i));
        }
        return this.projectRepository.saveAll(projects);
    }
    private void generateSampleTasks(int numberOfTasks) {
        Random random = new Random();
        List<Task> tasks = new ArrayList<>();
        List<Project> projects = this.projectRepository.findAll();
        projects.forEach(project -> {
            for(int i=1 ; i<=numberOfTasks +  random.nextInt(10); i++){
                tasks.add(Task.builder()
                        .project(project)
                        .title("Task-" +i)
                        .description("Description of task " +i)
                        .timeEstimation(10 + random.nextInt(60))
                        .actualTimeMinutes(20 + random.nextInt(60))
                        .doneAt(random.nextBoolean() ? LocalDateTime.now() : null)
                        .build());
            }
        });
        this.taskRepository.saveAll(tasks);
    }
}
