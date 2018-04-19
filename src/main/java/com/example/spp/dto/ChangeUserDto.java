package com.example.spp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeUserDto {
    private String email;
    private String password;
    private String name;
    private String biography;
    private String location;
    private String salary;
}
