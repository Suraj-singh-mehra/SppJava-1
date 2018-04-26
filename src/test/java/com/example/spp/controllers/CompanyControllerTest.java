package com.example.spp.controllers;

import com.example.spp.dto.AddNewCompanyDto;
import com.example.spp.models.Company;
import com.example.spp.repository.CompanyRepository;
import com.example.spp.service.TestDBCompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = "admin", authorities = "ADMINISTRATOR")
public class CompanyControllerTest extends BaseControllerTest {

    @MockBean
    private CompanyRepository companyRepository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private TestDBCompanyService testDBCompanyService;

    @Before
    public void setUpMock() {
        Company company1 = new Company();
        company1.setName("epam");
        company1.setEmail("first@email.com");
        company1.setId(1L);
        Company company2 = new Company();
        company2.setEmail("second@email.com");
        List<Company> companies = new ArrayList<>();
        companies.add(company1);
        companies.add(company2);
        when(companyRepository.findAll())
                .thenReturn(companies);
        when(companyRepository.save(any(Company.class))).thenReturn(null);
        when(companyRepository.findOne(any(Long.class))).thenReturn(company1);
    }

    @Test
    public void getCompanies() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/getAllDataFromCompanies")
                .param("_page", "1")
                .param("_limit", "10")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email", Matchers.equalTo("first@email.com")))
                .andExpect(jsonPath("$[1].email", Matchers.equalTo("second@email.com")));
    }

    @Test
    public void addNewCompany() throws Exception {
        AddNewCompanyDto addNewCompanyDto = new AddNewCompanyDto();
        addNewCompanyDto.setName("itransition");
        addNewCompanyDto.setEmail("some@email.com");
        addNewCompanyDto.setId(1);

        String json = mapper.writeValueAsString(addNewCompanyDto);
        MockHttpServletRequestBuilder requestBuilder = post("/addNewCompany")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.equalTo(true)));
    }

    @Test
    public void updateCompany() throws Exception {
        AddNewCompanyDto addNewCompanyDto = new AddNewCompanyDto();
        addNewCompanyDto.setName("epam");
        addNewCompanyDto.setEmail("some@email.com");
        addNewCompanyDto.setId(1);

        String json = mapper.writeValueAsString(addNewCompanyDto);
        MockHttpServletRequestBuilder requestBuilder = put("/updateCompany")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCompany() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = delete("/deleteCompany/1")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
}
