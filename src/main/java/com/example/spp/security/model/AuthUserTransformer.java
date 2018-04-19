package com.example.spp.security.model;

import com.example.spp.dto.AuthUserDto;
import com.example.spp.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthUserTransformer {

    public AuthUserDto makeDto(final User user) {
        AuthUserDto authUserDto = new AuthUserDto();

        authUserDto.setId(user.getId());
        authUserDto.setUsername(user.getEmail());
        authUserDto.setRole(user.getRole().name());

        return authUserDto;
    }

}