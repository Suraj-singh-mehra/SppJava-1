package com.example.spp.dto.registration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationResponseDto {
    private String token;

    public RegistrationResponseDto(final String token) {
        this.token = token;
    }
}
