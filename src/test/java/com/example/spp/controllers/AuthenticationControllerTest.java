package com.example.spp.controllers;

import com.example.spp.dto.login.LoginRequestDto;
import com.example.spp.models.User;
import com.example.spp.models.enums.UserRole;
import com.example.spp.models.enums.UserStatus;
import com.example.spp.repository.UserRepository;
import com.example.spp.security.model.AuthUserTransformer;
import com.example.spp.security.service.AuthenticationHelper;
import com.example.spp.service.AuthenticationService;
import com.example.spp.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthenticationControllerTest extends BaseControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    public AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private AuthUserTransformer authUserTransformer;

    @Autowired
    private AuthenticationHelper authenticationHelper;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Before
    public void setupMock() {
        User user1 = new User();
        user1.setEmail("first@email.com");
        user1.setRole(UserRole.ROLE_DRIVER);
        user1.setSalary(1000);
        user1.setFullname("User One");
        user1.setStatus(UserStatus.ACTIVE);
        List<User> users = new ArrayList<>();
        users.add(user1);
        when(userRepository.findAll())
                .thenReturn(users);
        when(userRepository.save(any(User.class))).thenReturn(null);
        when(userRepository.findOne(any(Long.class))).thenReturn(user1);
        when(userRepository.findByEmail(any(String.class))).thenReturn(user1);

    }

    @Test(expected = NestedServletException.class)
    public void login() throws Exception {
        LoginRequestDto dto = new LoginRequestDto();
        dto.setPassword("12345678");
        dto.setUsername("tsyulialisa2@gmail.com");

        String json = mapper.writeValueAsString(dto);

        MockHttpServletRequestBuilder requestBuilder = post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    public void logout() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = post("/auth/logout")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isUnauthorized());
    }

}
