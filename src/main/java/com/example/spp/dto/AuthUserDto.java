package com.example.spp.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserDto {
    private long id;
    private String username;
    private String role;
    private String status;
}
