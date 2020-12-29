package com.sda.company.controller;

import com.sda.company.models.Project;
import com.sda.company.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
@ControllerAdvice
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Project>> getAll(@RequestParam(defaultValue = "0") Integer pageNo,
                                                @RequestParam(defaultValue = "10") Integer pageSize,
                                                @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(projectService.getAll(pageNo, pageSize, sortBy));
    }

    @PostMapping("/create")
    public ResponseEntity<Project> create(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.create(project));
    }

    @DeleteMapping("/deleteById")
    public void deleteById(@RequestParam Long id) {
        projectService.deleteById(id);
    }

    @GetMapping("/findById")
    public ResponseEntity<Project> findById(@RequestParam Long id) {
        return ResponseEntity.ok(projectService.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Project> update(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.update(project));
    }

    @GetMapping("/findByName")
    public ResponseEntity<Project> findByName(@RequestParam String name) {
        return ResponseEntity.ok(projectService.findByName(name));
    }
}
