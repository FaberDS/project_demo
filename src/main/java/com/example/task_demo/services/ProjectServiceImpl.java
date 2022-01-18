package com.example.task_demo.services;

import com.example.task_demo.exceptions.EntityNotFoundException;
import com.example.task_demo.models.Project;
import com.example.task_demo.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> findAll() {
        return this.projectRepository.findAll();
    }

    @Override
    public Project findById(long projectId) {
        /*
        Long version
        Optional<Project> projectOptional = this.projectRepository.findById(projectId);
        if(projectOptional.isEmpty()){
            throw new EntityNotFoundException("TODO");
        }
        return projectOptional.get();
         */
        return this.projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException("Couldn't find project for id."));
    }

    @Override
    public Project addNew(Project project) {
        return this.projectRepository.save(project);
    }

    @Override
    public Project updateExisting(long projectId, Project updatedProject) {
        Project project = findById(projectId);
        project.setTitle(updatedProject.getTitle());
        return this.projectRepository.save(project);
    }

    @Override
    public void deleteProjectById(long projectId) {
        this.projectRepository.deleteById(projectId);
    }
}
