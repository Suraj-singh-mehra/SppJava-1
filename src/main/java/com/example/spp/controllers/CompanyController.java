package com.example.spp.controllers;

import com.example.spp.dto.AddNewCompanyDto;
import com.example.spp.models.Company;
import com.example.spp.rest.DBCompanyResponse;
import com.example.spp.service.TestDBCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanyController {
    @Autowired
    private TestDBCompanyService testDBCompanyService;

    @RequestMapping(value = "/getAllDataFromCompanies", method = RequestMethod.GET)
    public List<Company> getCompanies(@RequestParam(name = "_page") int page, @RequestParam(name = "_limit") int limit) {
        DBCompanyResponse response = testDBCompanyService.getCompanies();
        return response.getItems();
    }

    @RequestMapping(value = "/addNewCompany", method = RequestMethod.POST)
    public boolean addNewCompany(@Validated @RequestBody AddNewCompanyDto newCompanyDto) {
        return testDBCompanyService.addNewCompany(newCompanyDto);
    }

    @RequestMapping(value = "/updateCompany", method = RequestMethod.PUT)
    public void updateCompany(@Validated @RequestBody AddNewCompanyDto newCompanyDto) {
        testDBCompanyService.updateCompany(newCompanyDto);
    }

    @RequestMapping(value = "/deleteCompany/{id}", method = RequestMethod.DELETE)
    public void deleteCompany(@PathVariable("id") Long id) {
        testDBCompanyService.deleteCompany(id);
    }
}
