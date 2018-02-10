package com.example.spp.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddNewCompanyDto {
    @NotNull
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String email;
}
