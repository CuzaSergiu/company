package com.sda.company.mappers;


import com.sda.company.dto.CompanyCreateDto;
import com.sda.company.dto.CompanyInfoDto;
import com.sda.company.models.Company;

public class CompanyMapper {

    // == method == in metoda toEntity cream un obiect Company company,
    // unde apelam settters ( companyCreateDto.getName() - pentru a seta fieldurile din companyCreateDto )
    public static Company toEntity(CompanyCreateDto companyCreateDto) {
        Company company = new Company();
        company.setName(companyCreateDto.getName());
        company.setAddress(companyCreateDto.getAddress());
        company.setRegistrationNumber(companyCreateDto.getRegistrationNumber());
        company.setEmail(companyCreateDto.getEmail());
        company.setPhoneNumber(companyCreateDto.getPhoneNumber());

        return company;
    }

    //
    public static CompanyInfoDto toCompanyInfoDto(Company company) {
        CompanyInfoDto companyInfoDto = new CompanyInfoDto();
        companyInfoDto.setId(company.getId());
        companyInfoDto.setName(company.getName());
        companyInfoDto.setAddress(company.getAddress());
        companyInfoDto.setPhoneNumber(company.getPhoneNumber());
        companyInfoDto.setRegistrationNumber(company.getRegistrationNumber());
        companyInfoDto.setEmail(company.getEmail());

        return companyInfoDto;
    }

}
