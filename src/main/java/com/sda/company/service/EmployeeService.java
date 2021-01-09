package com.sda.company.service;

import com.sda.company.models.Employee;

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

    // metoda de update - ne ajuta sa editam
    Employee update(Employee employee);

    // metoda creatAll - ne populeaza baza de date cu employee dummy
    List<Employee> createALl(List<Employee> employees);

    // metoda assignProjectToEmployee - ne ajuta sa asignam un proiect unui employee
    void assignProjectToEmployee(Integer employeeId, Long projectId);

    // metoda getAllEmployeesBySpecificName - ne ajuta sa gasim un Employee dupa nume folosind un query
    List<Employee> getAllEmployeesBySpecificName(String name);
}
