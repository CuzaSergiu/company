package com.sda.company.service.impl;

import com.sda.company.exception.ProjectException;
import com.sda.company.models.Project;
import com.sda.company.repository.ProjectRepository;
import com.sda.company.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    // == constants ==
    private final ProjectRepository projectRepository;

    // == constructor ==
    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project create(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Project> projectPage = projectRepository.findAll(pageable);
        return projectPage.getContent();
    }

    @Override
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Project findById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectException("Project with id : " + id + " not found."));
    }

    @Override
    public Project update(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project findByName(String name) {
        return projectRepository.findByName(name)
                .orElseThrow(() -> new ProjectException("Project with name : " + name + " was not found."));
    }
}
