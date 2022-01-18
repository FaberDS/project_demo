package com.example.task_demo.services;

import com.example.task_demo.models.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasksForProject(long projectId);
    List<Task> getTasksForProject(long projectId, boolean filterDone);
    Task findById(long taskId);
    Task addTaskToProject(long projectId, Task task);
    Task updateTaskForProjectWithId(long projectId, long taskId, Task task);
    void deleteTaskForProjectById(long projectId, long taskId);
}
