package com.example.spp.controllers;

import com.example.spp.models.User;
import com.example.spp.repository.UserRepository;
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

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = "admin", authorities = "ADMINISTRATOR")
public class UsersControllerTest extends BaseControllerTest{

    @MockBean
    private UserRepository userRepository;

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

}