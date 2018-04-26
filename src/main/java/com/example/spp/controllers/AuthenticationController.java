package com.example.spp.controllers;

import com.example.spp.dto.AuthUserDto;
import com.example.spp.dto.login.LoginRequestDto;
import com.example.spp.dto.login.LoginResponseDto;
import com.example.spp.dto.registration.RegistrationRequestDto;
import com.example.spp.dto.registration.RegistrationResponseDto;
import com.example.spp.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    @ResponseStatus(value = HttpStatus.OK)
    public LoginResponseDto login(
            @RequestBody LoginRequestDto loginRequestDto
    ) {
        return authenticationService.login(loginRequestDto);
    }

    @PostMapping(value = "/registration")
    @ResponseStatus(value = HttpStatus.OK)
    public RegistrationResponseDto registration(
            @RequestBody final RegistrationRequestDto registrationRequestDto,
            HttpServletRequest request
    ) {
        String appUrl = request.getScheme() + "://" + request.getServerName() + ":4200";
        return authenticationService.register(registrationRequestDto, appUrl);
    }

    @GetMapping(value = "/me")
    @ResponseStatus(value = HttpStatus.OK)
    public AuthUserDto me() {
        return authenticationService.getMe();
    }
}
