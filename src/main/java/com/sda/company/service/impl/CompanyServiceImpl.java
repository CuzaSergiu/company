package com.sda.company.service.impl;

import com.sda.company.exception.CompanyException;
import com.sda.company.exception.EmployeeException;
import com.sda.company.models.Company;
import com.sda.company.repository.CompanyRepository;
import com.sda.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company create(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Company> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Company> companyPage = companyRepository.findAll(pageable);

        return companyPage.getContent();
    }

    @Override
    public void deleteById(Integer id) {
        companyRepository.deleteById(id);
    }

    // am folosit Optional sa verifice daca exista sau nu obiectul, ulterior am modificat cu Custom exception
    @Override
    public Company findById(Integer id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyException("Company with id : " + id + " not found."));
    }

    @Override
    public Company update(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company findByNameAndRegNumber(String name, Long regNumber) {
        return companyRepository.findByNameAndRegistrationNumber(name, regNumber)
                .orElseThrow(() -> new CompanyException("Company with name : "
                        + name + " and regNumber: "
                        + regNumber + " was not found."));

    }

    @Override
    public Company findByName(String name) {
        return companyRepository.findByName(name)
                .orElseThrow(() -> new EmployeeException("Company with name : " + name + " was not found."));
    }

    @Override
    public List<Company> createALl(List<Company> companies) {
        return (List<Company>) companyRepository.saveAll(companies);
    }
}
