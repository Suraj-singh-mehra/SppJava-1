package com.example.spp.service;

import com.example.spp.models.User;
import com.example.spp.models.enums.UserRole;
import com.example.spp.models.enums.UserStatus;
import com.example.spp.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class UserServiceTest extends BaseServiceTest{

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

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
        when(userRepository.findOne(any(Long.class))).thenReturn(user1);
        when(userRepository.findByEmail(any(String.class))).thenReturn(user1);
    }

    @Test
    public void save() throws Exception {
        User user = new User();
        user.setEmail("new@email.com");
        user.setLocation("Minsk");
        user.setStatus(UserStatus.ACTIVE);
        user.setRole(UserRole.ROLE_DRIVER);
        user.setPassword("1234567890");

        userService.save(user);
    }

    @Test
    public void create() throws Exception {
        User newUser = userService.create("New", "new", "new@email.com");
        assertEquals("New", newUser.getFullname());
        assertEquals("new@email.com", newUser.getEmail());
    }

    @Test
    public void findById() throws Exception {
        User user = userService.findById(1L);
        assertEquals(user.getEmail(), "first@email.com");
    }

}