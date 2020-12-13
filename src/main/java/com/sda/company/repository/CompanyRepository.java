package com.sda.company.repository;

import com.sda.company.models.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// adnotarea @Repository nu este obligatorie, dar este bine de mentionat
@Repository
public interface CompanyRepository extends CrudRepository<Company,Integer> {

}
