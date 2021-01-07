package com.sda.company.repository;

import com.sda.company.models.Company;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// annotarea @Repository nu este obligatorie, dar este bine de mentionat
// PagingAndSortingRepository - extinde CrudRepository
@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, Integer> {

    // ne va crea un query singur in functie de ce dorim noi din baza noastra
    Optional<Company> findByNameAndRegistrationNumber(String name, Long registrationNumber);


    // am verificat daca merge si fara Optional, merge,
    // am modificat inapoi in Optional pentru a putea chema exceptia noastra custom pe metoda
    Optional<Company> findByName(String name);

}
