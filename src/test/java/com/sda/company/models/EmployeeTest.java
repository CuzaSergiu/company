package com.sda.company.models;

import com.sda.company.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;


@DataJpaTest
class EmployeeTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    //testare pentru metoda findById
    @Test
    public void findById() {
        Optional<Employee> id;
        id = employeeRepository.findById(1);


        assertThat(id.get().getId())
                .isEqualTo(1);

    }

    //
    @Test
    public void findByName() {
        Employee name = new Employee();

        assertThat(name.getFirstName()).isEqualToIgnoringCase("cuza");
    }

}