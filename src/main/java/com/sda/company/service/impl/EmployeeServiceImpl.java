package com.sda.company.service.impl;


import com.sda.company.exception.EmployeeException;
import com.sda.company.models.Employee;
import com.sda.company.models.Project;
import com.sda.company.repository.EmployeeRepository;
import com.sda.company.repository.ProjectRepository;
import com.sda.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    // == constants ==
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    // == constructor ==
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    // == public methods ==
    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Employee> employeePage = employeeRepository.findAll(pageable);

        return employeePage.getContent();
    }

    @Override
    public void deleteById(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee findById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeException("Employee with id : " + id + " not found."));
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> createALl(List<Employee> employees) {
        return (List<Employee>) employeeRepository.saveAll(employees);
    }

    @Override
    public void assignProjectToEmployee(Integer employeeId, Long projectId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        Optional<Project> project = projectRepository.findById(projectId);

        employee.get().getProjectList().add(project.get());
        employeeRepository.save(employee.get());

        project.get().getEmployeeList().add(employee.get());
        projectRepository.save(project.get());
    }

    @Override
    public List<Employee> getAllEmployeesBySpecificName(String name) {
        return employeeRepository.getAllEmployeesBySpecificName(name);
    }
}
