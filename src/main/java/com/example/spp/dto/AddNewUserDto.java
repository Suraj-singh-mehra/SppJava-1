package com.example.spp.dto;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AddNewUserDto {
    @NotNull
    private long id;

    @NotNull
    @Size(min=1, max=25)
    private String fullname;

    @NotNull
    private String email;

    @NotNull
    private String status;

    @NotNull
    private String role;

    @NotNull
    @NumberFormat
    private int salary;
}
