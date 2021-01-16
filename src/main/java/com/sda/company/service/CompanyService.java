package com.sda.company.service;

import com.sda.company.dto.CompanyCreateDto;
import com.sda.company.dto.CompanyInfoDto;
import com.sda.company.models.Company;

import java.util.List;

public interface CompanyService {
    // aceasta este o semnatura, nu avem implementare in cazul de fata
    CompanyInfoDto create(CompanyCreateDto companyCreateDto);

    // metoda ne returneaza o lista cu toate companiile, pentru asta nu avem nevoie sa ii introducem parametrii
    List<Company> getAll(Integer pageNo, Integer pageSize, String sortBy);

    // metoda de delete nu returneaza nimic, sterge un item in functie de id
    void deleteById(Integer id);

    // metoda de findById, ne returneaza un obiect in functie de id
    Company findById(Integer id);

    Company update(Company company);

    Company findByNameAndRegNumber(String name, Long regNumber);

    Company findByName(String name);

    List<Company> createALl(List<Company> companies);

}
