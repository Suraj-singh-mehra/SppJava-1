package com.example.spp.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddNewStorageDto {
    @NotNull
    private long id;

    @NotNull
    private String address;

    @NotNull
    private int capacity;
}
