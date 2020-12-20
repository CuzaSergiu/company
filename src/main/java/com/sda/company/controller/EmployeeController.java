package com.sda.company.controller;

import com.github.javafaker.Faker;
import com.sda.company.components.CustomFakerEmployee;
import com.sda.company.models.Employee;
import com.sda.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


// pentru fiecare platforma se realizeaza un Controller custom
@RestController
@RequestMapping("/api/v1/employee")
@ControllerAdvice
// CompanyController realizeaza legatura dintre front-end si back-end
public class EmployeeController {

    private final EmployeeService employeeService;
    private final CustomFakerEmployee customFakerEmployee;

    @Autowired
    public EmployeeController(EmployeeService employeeService, CustomFakerEmployee customFakerEmployee) {
        this.employeeService = employeeService;
        this.customFakerEmployee = customFakerEmployee;
    }

    // ResponseEntity - transpune raspunsul in JSON
    // @RequestBody - primeste un JSON si il transforma automat de catre Spring in obiectul nostru, in cazul de fata Company
    @PostMapping("/create")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.create(employee));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Employee>> getAll(@RequestParam(defaultValue = "0") Integer pageNo,
                                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                                 @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(employeeService.getAll(pageNo, pageSize, sortBy));
    }

    @DeleteMapping("/deleteById")
    public void deleteById(@RequestParam Integer id) {
        employeeService.deleteById(id);
    }

    @GetMapping("/findById")
    //  RequestParam - ii spune programului ca va avea nevoie de un parametru de tip Integer id
    public Employee findById(@RequestParam Integer id) {
        return employeeService.findById(id);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.update(employee));
    }

    //aceasta metoda populeaza baza de date cu valorile introduse de noi, in cazul de fata employee random
    @GetMapping("/populate")
    public void populate() {

        employeeService.createALl(customFakerEmployee.createDummyEmployeeList());
    }
}
