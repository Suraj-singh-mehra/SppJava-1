package com.example.spp.rest;

import com.example.spp.models.Company;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DBCompanyResponse {
    private List<Company> items;
    private int totalItems;
}
