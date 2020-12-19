package com.sda.company.controller;

import com.github.javafaker.Faker;
import com.sda.company.models.Company;
import com.sda.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// pentru fiecare platforma se realizeaza un Controller custom
@RestController
@RequestMapping("/api/v1/company")
@ControllerAdvice
// CompanyController realizeaza legatura dintre front-end si back-end
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // ResponseEntity - transpune raspunsul in JSON, este obligatoriu in RestController ca si return type
    // @RequestBody - primeste un Body JSON si il transforma automat de catre Spring in obiectul nostru, in cazul de fata Company
    // Acesta este un API
    @PostMapping("/create")
    public ResponseEntity<Company> create(@RequestBody Company company) {
        return ResponseEntity.ok(companyService.create(company));
    }

    // prin aceasta metoda putem alege ce param folosim in front-end
    // daca nu sunt introduse valori la param se vor lua in considerare cele oferite in defaultValue
    @GetMapping("/getAll")
    public ResponseEntity<List<Company>> getAll(@RequestParam(defaultValue = "0") Integer pageNo,
                                                @RequestParam(defaultValue = "10") Integer pageSize,
                                                @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(companyService.getAll(pageNo, pageSize, sortBy));
    }

    // metoda de void nu are return niciodata
    @DeleteMapping("/deleteById")
    public void deleteById(@RequestParam Integer id) {
        companyService.deleteById(id);
    }

    // RequestParam - adnotarea ne mentioneaza ca vom introduce parametrii in API pentru a gasi un Company in functie de id
    // trebuie adnotat fiecare parametru
    @GetMapping("/findById")
    public ResponseEntity<Company> findById(@RequestParam Integer id) {
        return ResponseEntity.ok(companyService.findById(id));
    }

    //PathVariable - folosim {id} in adresa URL pentru a mentiona path-ul, path-ul este ceea ce ii introducem noi ca si parametru Integer id, in cazul de fata;
    @GetMapping("/findById/{id}")
    //in parametrii trebuie sa mentinem obligatoriu ordinea cand folosim @PathVariable
    //@GetMapping("/findById/{id}/{name}/{age}")
    public ResponseEntity<Company> findByIdByPath(@PathVariable Integer id) {
        return ResponseEntity.ok(companyService.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Company> update(@RequestBody Company company) {
        return ResponseEntity.ok(companyService.update(company));
    }

    // am folosit un query creat in Interfata CompanyRepository pentru a putea crea metoda de cautare dupa nume si regNumber
    @GetMapping("/findByNameAndRegNumber")
    public ResponseEntity<Company> findByNameAndRegNumber(@RequestParam String name, @RequestParam Long regNumber) {
        return ResponseEntity.ok(companyService.findByNameAndRegNumber(name, regNumber));
    }

    // am folosit un query fara Optional
    @GetMapping("/findByName")
    public ResponseEntity<Company> findByName(@RequestParam String name) {
        return ResponseEntity.ok(companyService.findByName(name));
    }

    @GetMapping("/populate")
    public void populate() {
        Faker faker = new Faker();
        List<Company> companies = new ArrayList<>();

        for (int i = 0; i < 201; i++) {
            Company company = new Company();
            company.setName(faker.company().name());
            company.setAddress(faker.address().fullAddress());
            company.setPhoneNumber(faker.phoneNumber().phoneNumber());
            company.setRegistrationNumber(faker.number().randomNumber(11, true));
            company.setEmail(faker.bothify("?????##@yahoo.com"));

            companies.add(company);
        }
        companyService.createALl(companies);
    }


}
