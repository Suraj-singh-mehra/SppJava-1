package com.example.spp.controllers;

import com.example.spp.dto.AddNewUserDto;
import com.example.spp.models.User;
import com.example.spp.repository.UserRepository;
import com.example.spp.service.AuthenticationService;
import com.example.spp.service.TestDBUsersService;
import com.example.spp.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = "admin", authorities = "ADMINISTRATOR")
public class UsersControllerTest extends BaseControllerTest{

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private TestDBUsersService testDBUsersService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @Before
    public void setupMock() {
        User user1 = new User();
        user1.setEmail("first@email.com");
        User user2 = new User();
        user2.setEmail("second@email.com");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        when(userRepository.findAll())
                .thenReturn(users);
        when(userRepository.save(any(User.class))).thenReturn(null);
    }

    @Test
    public void getUsers() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/getAllDataFromUsers")
                .param("_page", "1")
                .param("_limit", "10")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email", Matchers.equalTo("first@email.com")))
                .andExpect(jsonPath("$[1].email", Matchers.equalTo("second@email.com")));
    }

    @Test
    public void addNewUser() throws Exception {
        AddNewUserDto addNewUserDto = new AddNewUserDto();
        addNewUserDto.setStatus("ACTIVE");
        addNewUserDto.setSalary(1000);
        addNewUserDto.setRole("ROLE_ACCOUNTER");
        addNewUserDto.setFullname("Some User");
        addNewUserDto.setEmail("some@email.com");
        addNewUserDto.setId(1);

        String json = mapper.writeValueAsString(addNewUserDto);
        MockHttpServletRequestBuilder requestBuilder = post("/addNewUser")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.equalTo(true)));
    }



}