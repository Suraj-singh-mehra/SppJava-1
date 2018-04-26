package com.example.spp.service;

import com.example.spp.dto.AddNewCompanyDto;
import com.example.spp.models.Company;
import com.example.spp.repository.CompanyRepository;
import com.example.spp.rest.DBCompanyResponse;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

public class DBCompanyServiceTest extends BaseServiceTest{
    @Autowired
    TestDBCompanyService companyService;
    @MockBean
    private CompanyRepository companyRepository;

    @Before
    public void setupMock() {
        Company comp1 = new Company();
        comp1.setName("SPP");
        comp1.setEmail("masha.melnik@yandex.by");
        Company comp2 = new Company();
        comp2.setName("bla-bla");
        comp2.setEmail("test@test.com");

        List<Company> companies = new ArrayList<>();
        companies.add(comp1);
        companies.add(comp2);

        when(companyRepository.save(comp1)).thenReturn(comp1);
        when(companyRepository.save(comp2)).thenReturn(null);
        when(companyRepository.findOne(5L)).thenReturn(null);
        when(companyRepository.findOne(1L)).thenReturn(comp1);
        when(companyRepository.findAll()).thenReturn(companies);
    }

    @Test
    public void getCompanies() throws Exception {
        DBCompanyResponse response = companyService.getCompanies();
        assertEquals(2, response.getTotalItems());
    }

    @Test
    public void getCompaniesFailed() throws Exception {
        DBCompanyResponse response = companyService.getCompanies();
        assertNotEquals(0, response.getTotalItems());
    }

    @Test
    public void addNewCompany() throws Exception {
        AddNewCompanyDto newCompany = new AddNewCompanyDto();
        newCompany.setName("SPP");
        newCompany.setEmail("masha.melnik@yandex.by");

        assertFalse(companyService.addNewCompany(newCompany));
    }

    @Test
    public void addNewCompanyFailed() throws Exception {
        AddNewCompanyDto newCompany = new AddNewCompanyDto();
        newCompany.setName("bla-bla");
        newCompany.setEmail("test@test.com");

        assertTrue(companyService.addNewCompany(newCompany));
    }

    @Test
    public void updateSuccessCompany() throws Exception {
        AddNewCompanyDto company = new AddNewCompanyDto();
        company.setId(1L);
        company.setName("bla");
        company.setEmail("test@test.com");

        companyService.updateCompany(company);
    }

    @Test(expected = NullPointerException.class)
    public void updateFailedCompany() throws Exception {
        AddNewCompanyDto company = new AddNewCompanyDto();
        company.setId(5L);
        company.setName("gdf");
        company.setEmail("test@mail.ru");

        companyService.updateCompany(company);
    }

    @Test
    public void deleteCompany() throws Exception {
        companyService.deleteCompany(5L);
    }

}