package com.sda.company.service;

import com.sda.company.models.Company;

import java.util.List;

public interface CompanyService {
     // aceasta este o semnatura, nu avem implementare in cazul de fata
    Company create(Company company);

    // metoda ne returneaza o lista cu toate companiile, pentru asta nu avem nevoie sa ii introducem parametrii
    List<Company> getAll();

}
