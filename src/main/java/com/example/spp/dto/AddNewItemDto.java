package com.example.spp.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddNewItemDto {
    @NotNull
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String category;

    @NotNull
    private String expiryDate;

    @NotNull
    private int price;
}
