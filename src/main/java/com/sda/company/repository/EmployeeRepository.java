package com.sda.company.repository;


import com.sda.company.models.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// annotarea @Repository nu este obligatorie, dar este bine de mentionat
@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

    // == Query methods ==
    // @Query - ne ajuta sa facem API bazate pe un query
    @Query("select e from Employee e where e.firstName like :name%")
    List<Employee> getAllEmployeesBySpecificName(@Param("name") String name);

}
