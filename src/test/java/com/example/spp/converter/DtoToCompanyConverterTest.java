package com.example.spp.converter;

import com.example.spp.dto.AddNewCompanyDto;
import com.example.spp.models.Company;
import org.junit.Test;

import static org.junit.Assert.*;

public class DtoToCompanyConverterTest {
    @Test
    public void convertToCompany() throws Exception {
        AddNewCompanyDto dto = new AddNewCompanyDto();
        dto.setEmail("test@email.com");
        dto.setName("someCompany");

        Company company = DtoToCompanyConverter.convertToCompany(dto);

        assertEquals("someCompany", company.getName());
        assertEquals("test@email.com", company.getEmail());
    }

}