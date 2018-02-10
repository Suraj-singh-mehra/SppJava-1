package com.example.spp.service;


import com.example.spp.converter.DtoToCompanyConverter;
import com.example.spp.dto.AddNewCompanyDto;
import com.example.spp.models.Company;
import com.example.spp.repository.CompanyRepository;
import com.example.spp.rest.DBCompanyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TestDBCompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public DBCompanyResponse getCompanies() {
        DBCompanyResponse response = new DBCompanyResponse();
        List<Company> companies = companyRepository.findAll();
        response.setItems(companies);
        response.setTotalItems(companies.size());
        return response;
    }

    public boolean addNewCompany(AddNewCompanyDto newCompanyDto) {
        boolean result = true;
        Company savedItem = companyRepository.save(DtoToCompanyConverter.convertToCompany(newCompanyDto));
        if (savedItem != null) {
            result = false;
        }
        return result;
    }

    public void updateCompany(AddNewCompanyDto newCompanyDto) {
        Company company = companyRepository.findOne(newCompanyDto.getId());
        company.setName(newCompanyDto.getName());
        company.setEmail(newCompanyDto.getEmail());
        companyRepository.save(company);
    }

    public void deleteCompany(Long id) {
        companyRepository.delete(id);
    }
}