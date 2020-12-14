package com.sda.company.controller;

import com.sda.company.models.Company;
import com.sda.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// pentru fiecare platforma se realizeaza un Controller custom
@RestController
@RequestMapping("/api/v1/company")
// CompanyController realizeaza legatura dintre front-end si back-end
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // ResponseEntity - transpune raspunsul in JSON
    // @RequestBody - primeste un JSON si il transforma automat de catre Spring in obiectul nostru, in cazul de fata Company
    @PostMapping("/create")
    public ResponseEntity<Company> create(@RequestBody Company company){
        return ResponseEntity.ok(companyService.create(company));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Company>> getAll(){
        return ResponseEntity.ok(companyService.getAll());
    }

    // metoda de void nu are return niciodata
    @DeleteMapping("/deleteById")
    public void deleteById(Integer id){
        companyService.deleteById(id);
    }

    @GetMapping("/findById")
    public Optional<Company> findById(Integer id){
        return companyService.findById(id);
    }

}
