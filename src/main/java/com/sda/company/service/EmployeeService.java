package com.sda.company.service;


import com.sda.company.models.Employee;

import java.util.List;

public interface EmployeeService {
     // aceasta este o semnatura, nu avem implementare in cazul de fata
    Employee create(Employee employee);

    // metoda ne returneaza o lista cu toti employee, pentru asta nu avem nevoie sa ii introducem parametrii
    List<Employee> getAll();

    void deleteById(Integer id);

}
