package com.example.spp.converter;

import com.example.spp.dto.registration.RegistrationRequestDto;
import com.example.spp.dto.registration.RegistrationResponseDto;
import com.example.spp.models.User;
import com.example.spp.repository.UserRepository;
import com.example.spp.service.AuthenticationService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.Assert.*;

public class AuthenticationServiceTest {
    @Test
    public void registrationTest() throws Exception {
        AuthenticationService authService = new AuthenticationService();
        authService.userRepository = new UserRepository();
        RegistrationRequestDto registrationRequest = new RegistrationRequestDto();

        registrationRequest.setUsername("test");
        registrationRequest.setEmail("test@mail.ru");
        registrationRequest.setPassword("12121212");

        RegistrationResponseDto response = authService.register(registrationRequest, "test");
        assertEquals(true, (response.getToken() != null));
    }

}