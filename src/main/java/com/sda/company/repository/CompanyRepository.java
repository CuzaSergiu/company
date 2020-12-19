package com.sda.company.repository;

import com.sda.company.models.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// adnotarea @Repository nu este obligatorie, dar este bine de mentionat
// PagingAndSortingRepository - extinde CrudRepository
@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, Integer> {

    // ne va crea un query singur in functie de ce dorim noi din baza noastra
    Optional<Company> findByNameAndRegistrationNumber(String name, Long registrationNumber);

    Company findByName(String name);

}
