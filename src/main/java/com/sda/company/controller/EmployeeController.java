package com.sda.company.controller;

import com.sda.company.models.Employee;
import com.sda.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// pentru fiecare platforma se realizeaza un Controller custom
@RestController
@RequestMapping("/api/v1/employee")
// CompanyController realizeaza legatura dintre front-end si back-end
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // ResponseEntity - transpune raspunsul in JSON
    // @RequestBody - primeste un JSON si il transforma automat de catre Spring in obiectul nostru, in cazul de fata Company
    @PostMapping("/create")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.create(employee));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @DeleteMapping("/deleteById")
    public void deleteById(Integer id) {
        employeeService.deleteById(id);
    }

    @GetMapping("/findById")
    public Optional<Employee> findById(Integer id) {
        return employeeService.findById(id);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.update(employee));
    }

//    //todo nu se face update, in schimb imi modifica in null, de corectat
//    @PutMapping("/update")
//    public Optional<Employee> update(Integer id) {
//        return employeeService.updateFirstNameById(id);
//    }
}
