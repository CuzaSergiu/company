package com.sda.company.components;

import com.github.javafaker.Faker;
import com.sda.company.models.Company;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// @Component - annotarea care ne modifica clasa intr-un component, @ComponentScan il gaseste,
// prin constructor este injectat in metodele noastre
@Component
public class CustomFakerCompany {

    public List<Company> createDummyCompanyList() {
        Faker faker = new Faker();
        List<Company> companies = new ArrayList<>();

        for (int i = 0; i < 250; i++) {
            Company company = new Company();
            company.setName(faker.company().name());
            company.setAddress(faker.address().fullAddress());
            company.setPhoneNumber(faker.phoneNumber().phoneNumber());
            company.setRegistrationNumber(faker.number().randomNumber(11, true));
            company.setEmail(faker.bothify("?????##@yahoo.com"));

            companies.add(company);
        }
        return companies;
    }


}
