package com.example.spp.converter;

import com.example.spp.dto.AddNewCompanyDto;
import com.example.spp.models.Company;

public class DtoToCompanyConverter {
    public static Company convertToCompany(AddNewCompanyDto companyDto) {
        Company company = new Company();
        company.setEmail(companyDto.getEmail());
        company.setName(companyDto.getName());
        return company;
    }
}
