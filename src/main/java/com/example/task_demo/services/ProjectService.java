package com.example.task_demo.services;

import com.example.task_demo.models.Project;

import java.util.List;

public interface ProjectService {
    List<Project> findAll();
    Project findById(long projectId);
    Project addNew(Project project);
    Project updateExisting(long projectId, Project project);
    void deleteProjectById(long projectId);
}
