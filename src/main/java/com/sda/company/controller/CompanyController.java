package com.sda.company.controller;


import com.sda.company.components.CustomFakerCompany;
import com.sda.company.models.Company;
import com.sda.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// pentru fiecare platforma se realizeaza un Controller custom
@RestController
@RequestMapping("/api/v1/company")
@ControllerAdvice
// CompanyController realizeaza legatura dintre front-end si back-end
public class CompanyController {

    //apelam clasele, le facem privat si final, ulterior le plasam intr-un constructor pentru a se putea face injectarea
    private final CompanyService companyService;
    private final CustomFakerCompany customFakerCompany;

    // injectam companyService,customFaker printr-un constructor la care ii setam annotarea @Autowired
    // @Autowired - enables you to inject the object dependency
    @Autowired
    public CompanyController(CompanyService companyService, CustomFakerCompany customFakerCompany) {
        this.companyService = companyService;
        this.customFakerCompany = customFakerCompany;
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

    // RequestParam - annotarea ne mentioneaza ca vom introduce parametrii in API pentru a gasi un Company in functie de id
    // trebuie annotat fiecare parametru
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

    //aceasta metoda populeaza baza de date cu valorile introduse de noi, in cazul de fata companii random
    @GetMapping("/populate")
    public void populate() {
        companyService.createALl(customFakerCompany.createDummyCompanyList());
    }


}
