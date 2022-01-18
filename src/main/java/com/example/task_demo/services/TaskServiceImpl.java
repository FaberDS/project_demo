package com.example.task_demo.services;

import com.example.task_demo.exceptions.EntityNotFoundException;
import com.example.task_demo.models.Project;
import com.example.task_demo.models.Task;
import com.example.task_demo.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private ProjectService projectService;

    public TaskServiceImpl(ProjectService projectService, TaskRepository taskRepository) {
        this.projectService = projectService;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasksForProject(long projectId) {
        Project project = projectService.findById(projectId);
        return taskRepository.findAllByProject(project);
    }

    @Override
    public List<Task> getTasksForProject(long projectId, boolean filterDone) {
        Project project = projectService.findById(projectId);
        return filterDone ? taskRepository.findAllDoneTasksByProject(project) : taskRepository.findAllOpenTasksByProject(project);
    }

    @Override
    public Task findById(long taskId) {
        return taskRepository.findById(taskId).orElseThrow(()-> new EntityNotFoundException("No task with id " + taskId + " could be found."));
    }

    @Override
    public Task addTaskToProject(long projectId, Task task) {
        Project project = projectService.findById(projectId);
        task.setProject(project);
        return taskRepository.save(task);
    }

    @Override
    public Task updateTaskForProjectWithId(long projectId, long taskId, Task updatedTask) {
        Project project = projectService.findById(projectId);
        Task task = findById(taskId);
        task.setProject(project);
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setActualTimeMinutes(updatedTask.getActualTimeMinutes());
        task.setDoneAt(updatedTask.getDoneAt());
        task.setTimeEstimation(updatedTask.getTimeEstimation());
        return taskRepository.save(task);
    }

    @Override
    public void deleteTaskForProjectById(long projectId, long taskId) {
        Project project = projectService.findById(projectId);
        Task task = findById(taskId);
        taskRepository.delete(task);
    }
}
