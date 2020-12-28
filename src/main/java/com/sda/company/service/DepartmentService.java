package com.sda.company.service;

import com.sda.company.models.Department;

import java.util.List;

public interface DepartmentService {
    // aceasta este o semnatura, nu avem implementare in cazul de fata
    Department create(Department department);

    // metoda ne returneaza o lista cu toate departamentele, pentru asta nu avem nevoie sa ii introducem parametrii
    List<Department> getAll(Integer pageNo, Integer pageSize, String sortBy);

    // metoda de delete nu returneaza nimic, sterge un item in functie de id
    void deleteById(Long id);

    // metoda de findById, ne returneaza un obiect in functie de id
    Department findById(Long id);

    Department update(Department department);

    Department findByName(String name);


}
