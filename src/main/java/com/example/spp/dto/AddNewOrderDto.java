package com.example.spp.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddNewOrderDto {
    @NotNull
    private long id;

    @NotNull
    private int amount;

    @NotNull
    private int totalPrice;

    @NotNull
    private String date;

    @NotNull
    private boolean wasThere;

}
