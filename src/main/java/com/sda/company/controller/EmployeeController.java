package com.sda.company.controller;

import com.sda.company.components.CustomFakerEmployee;
import com.sda.company.models.Employee;
import com.sda.company.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
// pentru fiecare platforma se realizeaza un Controller custom
@RestController
@RequestMapping("/api/v1/employee")
@ControllerAdvice
// CompanyController realizeaza legatura dintre front-end si back-end
public class EmployeeController {

    // == constants ==
    private final EmployeeService employeeService;
    private final CustomFakerEmployee customFakerEmployee;

    // == constructor ==
    @Autowired
    public EmployeeController(EmployeeService employeeService, CustomFakerEmployee customFakerEmployee) {
        this.employeeService = employeeService;
        this.customFakerEmployee = customFakerEmployee;
    }

    // == public methods ==
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
        log.info("Get All method called!");
        return ResponseEntity.ok(employeeService.getAll(pageNo, pageSize, sortBy));
    }

    @DeleteMapping("/deleteById")
    public void deleteById(@RequestParam Integer id) {
        employeeService.deleteById(id);
        log.info("Item deleted, id = {}", id);
    }

    @GetMapping("/findById")
    //  RequestParam - ii spune programului ca va avea nevoie de un parametru de tip Integer id
    public Employee findById(@RequestParam Integer id) {
        log.info("FindById method called, id = {}", id);
        return employeeService.findById(id);
    }

    // aceasta metoda ne permite sa editam un employee cu ajutorul unui body JSON
    @PutMapping("/update")
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        log.info("Update method called !");
        return ResponseEntity.ok(employeeService.update(employee));
    }

    // aceasta metoda populeaza baza de date cu valorile introduse de noi, in cazul de fata employee random
    @GetMapping("/populate")
    public void populate() {
        employeeService.createALl(customFakerEmployee.createDummyEmployeeList());
    }

    @GetMapping("/getDepartment&Project")
    public String getDepartmentAndProject(@RequestParam Integer id) {
        Employee employee = employeeService.findById(id);
        log.info("Get department and project from employee, employeeId = {}", id);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Department: ").append(employee.getDepartment().getName()).append("\nProject: ");
        employee.getProjectList().forEach(project -> stringBuilder.append(project.getName()).append(" "));
        return stringBuilder.toString();
    }

    @GetMapping("/assignProject")
    public void assignProject(@RequestParam Integer employeeId,
                              @RequestParam Long projectId) {
        employeeService.assignProjectToEmployee(employeeId, projectId);
        log.info("The employee = {} has assigned project = {}", employeeId, projectId);
    }

    @GetMapping("/findBySpecificName")
    public ResponseEntity<List<Employee>> findBySpecificName(@RequestParam String name) {
        log.info("findBySpecificName method called with name = {}", name);
        return ResponseEntity.ok(employeeService.getAllEmployeesBySpecificName(name));
    }
}
