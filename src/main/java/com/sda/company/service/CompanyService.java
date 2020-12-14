package com.sda.company.service;

import com.sda.company.models.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    // aceasta este o semnatura, nu avem implementare in cazul de fata
    Company create(Company company);

    // metoda ne returneaza o lista cu toate companiile, pentru asta nu avem nevoie sa ii introducem parametrii
    List<Company> getAll();

    // metoda de delete nu returneaza nimic, sterge un item in functie de id
    void deleteById(Integer id);

    // metoda de findById, ne returneaza un obiect in functie de id
    Optional<Company> findById(Integer id);

    Optional<Company> updateByID(Integer id, String name);

}
