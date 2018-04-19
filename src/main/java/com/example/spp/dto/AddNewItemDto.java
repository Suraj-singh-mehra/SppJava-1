package com.example.spp.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class AddNewItemDto {
    @NotNull
    private long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String category;

    @NotNull
    private int price;
}
