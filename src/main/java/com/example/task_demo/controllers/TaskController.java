package com.example.task_demo.controllers;

import com.example.task_demo.models.Task;
import com.example.task_demo.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value="/{projectId}/tasks")
    public List<Task> getAllTasksForProjectId(@PathVariable long projectId){
        return  taskService.getAllTasksForProject(projectId);
    }

    @GetMapping(value="/{projectId}/tasks",params = "done")
    public List<Task> getFilterTasksForProjectId(@PathVariable long projectId, @RequestParam boolean done){
        return  taskService.getTasksForProject(projectId,done);
    }

    @PostMapping("/{projectId}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task addNewTaskToProject(@PathVariable long projectId, @RequestBody @Valid Task task){
        return taskService.addTaskToProject(projectId,task);
    }

    @PutMapping("/{projectId}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Task updateTaskByIdForProject(@PathVariable long projectId, @PathVariable long taskId, @RequestBody @Valid Task task){
        return taskService.updateTaskForProjectWithId(projectId,taskId,task);
    }

    @DeleteMapping("/{projectId}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaskByIdForProject(@PathVariable long projectId, @PathVariable long taskId){
        taskService.deleteTaskForProjectById(projectId,taskId);
    }
}
