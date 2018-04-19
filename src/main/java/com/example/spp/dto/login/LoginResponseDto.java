package com.example.spp.dto.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponseDto {
    private String token;

    public LoginResponseDto(final String token) {
        this.token = token;
    }
}
