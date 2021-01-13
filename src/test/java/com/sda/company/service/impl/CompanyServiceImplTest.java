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

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    // test care ne demonstreaza crearea unui obiect nou de tip Company
    @Test
    void create() {
        Company company = new Company();
        company.setName("Test");
        Company result = new Company();
        company.setName("Test");

        Mockito.when(companyService.create(company)).thenReturn(result);

        Company c = companyRepository.save(company);

        Mockito.verify(companyRepository, Mockito.times(1)).save(company);

        Assertions.assertEquals(result, c);

    }

    // test care ne returneaza toate companiile
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

    // this care demonstreaza stergerea unei company, cu ajutorul id-ului
    @Test
    void deleteById() {
        Company company = new Company();
        company.setId(1);

        Mockito.when(companyRepository.findById(1)).thenReturn(Optional.of(company));

        //when
        companyService.deleteById(1);

        //then
        Mockito.verify(companyRepository, Mockito.times(1)).deleteById(1);

    }

    // this care ne demonstreaza gasirea unui obiect de tip Company cu ajutorul unui id
    @Test
    void findById() {

        Company company = new Company();

        Mockito.when(companyRepository.findById(1)).thenReturn(Optional.of(company));

        Optional<Company> result = companyRepository.findById(1);

        Mockito.verify(companyRepository, Mockito.times(1)).findById(1);

        Assertions.assertEquals(result.get(), company);


    }

    // test care ne demonstreaza editarea unui obiect de tip Company
    @Test
    void update() {
        Company company = new Company();
        company.setId(1);
        company.setName("name");

        Mockito.when(companyRepository.findById(1)).thenReturn(Optional.of(company));
        Mockito.when(companyRepository.save(company)).thenReturn(company);

        companyService.update(company);

        Mockito.verify(companyRepository, Mockito.times(1)).save(company);

        Assertions.assertEquals(company.getName(), company);

    }

    // test care ne demonstreaza gasisrea unui obiect de tip Company cu ajutorul numelui si regNumber
    @Test
    void findByNameAndRegNumber() {

        Company company = new Company();

        Mockito.when(companyRepository.findByNameAndRegistrationNumber("test1", 200L))
                .thenReturn(Optional.of(company));

        Optional<Company> result = companyRepository
                .findByNameAndRegistrationNumber("test1", 200L);

        Mockito.verify(companyRepository, Mockito.times(1))
                .findByNameAndRegistrationNumber("test1", 200L);

        // pentru verificare cu Optional trebuie sa apelam .get() pe result
        Assertions.assertEquals(result.get(), company);

        // todo - cand rulez testul este ok, am facut debug si imi apare o eroare, de ce? Verificarea se face cu debug pe methoda

    }

    // test care ne demonstreaza gasisrea unui obiect de tip Company cu ajutorul numelui
    @Test
    void findByName() {

        Company company = new Company();

        Mockito.when(companyRepository.findByName("name")).thenReturn(Optional.of(company));

        Optional<Company> result = companyRepository.findByName("name");

        Mockito.verify(companyRepository, Mockito.times(1)).findByName("name");

        Assertions.assertEquals(result.get(), company);

    }
}