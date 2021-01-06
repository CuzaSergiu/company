package com.sda.company.service;

import com.sda.company.models.Department;
import com.sda.company.models.Employee;
import com.sda.company.models.Project;

import java.util.List;

public interface EmployeeService {
    // aceasta este o semnatura, nu avem implementare in cazul de fata
    Employee create(Employee employee);

    // metoda ne returneaza o lista cu toti employee, pentru asta nu avem nevoie sa ii introducem parametrii
    List<Employee> getAll(Integer pageNo, Integer pageSize, String sortBy);

    // metoda deleteById
    void deleteById(Integer id);

    // metoda de findById, ne returneaza un obiect in functie de id
    Employee findById(Integer id);

    Employee update(Employee employee);

    List<Employee> createALl(List<Employee> employees);

    Employee checkDepartmentAndProject(Department department, Project project);
}
