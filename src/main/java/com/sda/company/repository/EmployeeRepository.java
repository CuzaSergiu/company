package com.sda.company.repository;


import com.sda.company.models.Department;
import com.sda.company.models.Employee;
import com.sda.company.models.Project;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

// annotarea @Repository nu este obligatorie, dar este bine de mentionat
@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

    Optional<Employee> findEmployeeByProjectListAndDepartment(Department department, Project project);

}
