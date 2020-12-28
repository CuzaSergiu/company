package com.sda.company.service;

import com.sda.company.models.Department;
import com.sda.company.models.Project;

import java.util.List;

public interface ProjectService {
    // aceasta este o semnatura, nu avem implementare in cazul de fata
    Project create(Project project);

    // metoda ne returneaza o lista cu toate departamentele, pentru asta nu avem nevoie sa ii introducem parametrii
    List<Project> getAll(Integer pageNo, Integer pageSize, String sortBy);

    // metoda de delete nu returneaza nimic, sterge un item in functie de id
    void deleteById(Long id);

    // metoda de findById, ne returneaza un obiect in functie de id
    Project findById(Long id);

    Project update(Project project);

    Project findByName(String name);


}
