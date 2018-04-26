package com.example.spp.converter;

import com.example.spp.dto.ChangeUserDto;
import com.example.spp.dto.login.LoginRequestDto;
import com.example.spp.dto.login.LoginResponseDto;
import com.example.spp.dto.registration.RegistrationRequestDto;
import com.example.spp.dto.registration.RegistrationResponseDto;
import com.example.spp.models.User;
import com.example.spp.repository.UserRepository;
import com.example.spp.security.exception.JsonException;
import com.example.spp.security.model.AuthUserTransformer;
import com.example.spp.security.service.AuthenticationHelper;
import com.example.spp.service.AuthenticationService;
import com.example.spp.service.BaseServiceTest;
import com.example.spp.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest extends BaseServiceTest{
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authService;
    @Autowired
    private AuthUserTransformer authUserTransformer;
    @Autowired
    private AuthenticationHelper authenticationHelper;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Before
    public void setupMock() {
        User user1 = new User();
        user1.setEmail("test@email.ru");

        when(userRepository.save(any(User.class))).thenReturn(null);
        when(userRepository.findOne(any(Long.class))).thenReturn(user1);
        when(userRepository.findByEmail("test@mail.ru")).thenReturn(user1);
    }

    @Test(expected = JsonException.class)
    public void registrationFailTest() throws Exception {
        RegistrationRequestDto registrationRequest = new RegistrationRequestDto();

        registrationRequest.setUsername("test");
        registrationRequest.setEmail("test@mail.ru");
        registrationRequest.setPassword("12121212");

        RegistrationResponseDto response = authService.register(registrationRequest, "test");
    }

    @Test(expected = InternalAuthenticationServiceException.class)
    public void loginTest(){
        LoginRequestDto loginRequest = new LoginRequestDto();

        loginRequest.setUsername("test");
        loginRequest.setPassword("12121212");

        LoginResponseDto response = authService.login(loginRequest);
    }

    @Test(expected = InternalAuthenticationServiceException.class)
    public void checkUserTest()
    {
        ChangeUserDto changeRequest = new ChangeUserDto();

        changeRequest.setEmail("test@mail.com");
        changeRequest.setPassword("12121212");
        changeRequest.setName("Maria");
        changeRequest.setBiography("bla-bla-bla");
        changeRequest.setLocation("");
        changeRequest.setSalary("1500");

        authService.checkUser(changeRequest);
    }
}