package com.sda.company.repository;


import com.sda.company.models.Department;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// annotarea @Repository nu este obligatorie, dar este bine de mentionat
@Repository
public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {

    Optional<Department> findByName(String name);
}
