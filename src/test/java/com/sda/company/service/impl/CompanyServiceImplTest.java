package com.sda.company.service.impl;

import com.sda.company.models.Company;
import com.sda.company.repository.CompanyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// @ExtendWith - trebuie annotata clasa de testing
@ExtendWith(SpringExtension.class)
class CompanyServiceImplTest {

    // injectam prin @InjectMocks in testare,
    // nu se injecteaza interfetele, trebuie injectata implementarea
    @InjectMocks
    private CompanyServiceImpl companyService;

    @Mock
    private CompanyRepository companyRepository;

    @Test
    void create() {
    }

    @Test
    void getAll() {

        Company company = new Company();
        company.setId(1);
        company.setName("Test1.name");
        company.setAddress("tes nr 2.adress");

        Pageable pageable = PageRequest.of(0, 5, Sort.by("id"));
        Page<Company> page = new PageImpl<>(Arrays.asList(company));

        // Mockito, cand o sa chemam companyRepository te rog sa imi returnezi o lista de company
        Mockito.when(companyRepository.findAll(pageable)).thenReturn(page);

        // in companyList obtinem rezultatul in urma apelului de functia de service ( functia return )
        List<Company> companyList = companyService.getAll(0, 5, "id");

        // Mockito, verifica ca findAll(pageable) a fost apelata o singura data in companyRepository
        Mockito.verify(companyRepository, Mockito.times(1)).findAll(pageable);

        // Assertions.assertEquals - comparam rezultatul obtinut(companyList) cu cel asteptat(Arrays.asList(company))
        Assertions.assertEquals(companyList, Arrays.asList(company));

    }

    @Test
    void deleteById() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void findByNameAndRegNumber() {
    }

    @Test
    void findByName() {
    }

    @Test
    void createALl() {
    }
}