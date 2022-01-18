package com.example.task_demo.controllers;

import com.example.task_demo.models.Project;
import com.example.task_demo.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getAllProjects(){
        return projectService.findAll();
    }
    @GetMapping("/{projectId}")
    public Project getProjectById(@PathVariable long projectId){
        return projectService.findById(projectId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project createNewProject(@RequestBody Project project){
        return projectService.addNew(project);
    }

    @PutMapping("/{projectId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Project updateProject(@PathVariable long projectId,@RequestBody Project project){
        return projectService.updateExisting(projectId,project);
    }

    @DeleteMapping("/{projectId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable long projectId){
        projectService.deleteProjectById(projectId);
    }
}
