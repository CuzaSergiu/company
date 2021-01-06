package com.sda.company.controller;

import com.sda.company.models.Department;
import com.sda.company.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/department")
@ControllerAdvice
public class DepartmentController {

    // == constants ==
    private final DepartmentService departmentService;

    // == constructor ==
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // == request methods ==
    @GetMapping("/getAll")
    public ResponseEntity<List<Department>> getAll(@RequestParam(defaultValue = "0") Integer pageNo,
                                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                                   @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(departmentService.getAll(pageNo, pageSize, sortBy));
    }

    @PostMapping("/create")
    public ResponseEntity<Department> create(@RequestBody Department department) {
        return ResponseEntity.ok(departmentService.create(department));
    }

    @DeleteMapping("/deleteById")
    public void deleteById(@RequestParam Long id) {
        departmentService.deleteById(id);
    }

    @GetMapping("/findById")
    public ResponseEntity<Department> findById(@RequestParam Long id) {
        return ResponseEntity.ok(departmentService.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Department> update(@RequestBody Department department) {
        return ResponseEntity.ok(departmentService.update(department));
    }

    @GetMapping("/findByName")
    public ResponseEntity<Department> findByName(@RequestParam String name) {
        return ResponseEntity.ok(departmentService.findByName(name));
    }

}
