package com.sda.company.components;

import com.github.javafaker.Faker;
import com.sda.company.models.Employee;

import java.util.ArrayList;
import java.util.List;

public class CustomFakerEmployee {

    public List<Employee> createDummyEmployeeList() {
        Faker faker = new Faker();
        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            Employee employee = new Employee();
            employee.setFirstName(faker.name().firstName());
            employee.setLastName(faker.name().lastName());
            employee.setAddress(faker.address().fullAddress());
            employee.setPhoneNumber(faker.phoneNumber().phoneNumber());
            employee.setPersonalNumericCode(faker.number().randomNumber(13, true));
            employee.setEmail(faker.bothify("?????##@yahoo.com"));

            employees.add(employee);
        }
        return employees;
    }


}
