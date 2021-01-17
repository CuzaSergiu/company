package com.sda.company.service.impl;

import com.sda.company.dto.CompanyCreateDto;
import com.sda.company.dto.CompanyInfoDto;
import com.sda.company.mappers.CompanyMapper;
import com.sda.company.models.Company;
import com.sda.company.repository.CompanyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

// @ExtendWith - trebuie annotata clasa de testing
@ExtendWith(SpringExtension.class)
class CompanyServiceImplTest {

    // injectam prin @InjectMocks in testare,
    // nu se injecteaza interfetele, trebuie injectata implementarea
    @InjectMocks
    private CompanyServiceImpl companyService;

    @Mock
    // @Mock - iti creaza o dublura a clasei cu toate dependintele ei
    private CompanyRepository companyRepository;

    @Mock
    private CompanyServiceImpl companyServiceMock;

    // test care ne demonstreaza crearea unui obiect nou de tip Company
    @Test
    void create() {
        CompanyCreateDto companyCreateDto = new CompanyCreateDto();
        companyCreateDto.setName("Test");
        companyCreateDto.setAddress("a");
        companyCreateDto.setEmail("@email.com");
        companyCreateDto.setPhoneNumber("12");
        companyCreateDto.setRegistrationNumber(1L);

        CompanyInfoDto result = new CompanyInfoDto();
        result.setId(1);
        result.setName("Test");
        result.setAddress("a");
        result.setRegistrationNumber(1L);
        result.setPhoneNumber("12");
        result.setEmail("@email.com");

        Company company = new Company();
        company.setId(1);
        company.setName("Test");
        company.setAddress("a");
        company.setRegistrationNumber(1L);
        company.setPhoneNumber("12");
        company.setEmail("@email.com");

        Mockito.when(companyRepository.save(Mockito.any())).thenReturn(company);

        CompanyInfoDto c = companyService.create(companyCreateDto);

        Mockito.verify(companyRepository, Mockito.times(1)).save(Mockito.any());

        Assertions.assertEquals(result.getName(), c.getName());

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
        Company input = new Company();
        input.setName("name");
        Company output = new Company();
        output.setName("name");

        Mockito.when(companyServiceMock.update(input)).thenReturn(output);

        Company c = companyServiceMock.update(input);

        Mockito.verify(companyServiceMock, Mockito.times(1)).update(input);

        Assertions.assertEquals(output, c);

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