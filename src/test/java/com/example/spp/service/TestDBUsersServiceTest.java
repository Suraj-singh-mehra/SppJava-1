package com.example.spp.service;

import com.example.spp.dto.AddNewUserDto;
import com.example.spp.models.User;
import com.example.spp.models.enums.UserRole;
import com.example.spp.models.enums.UserStatus;
import com.example.spp.repository.UserRepository;
import com.example.spp.rest.DBUsersResponse;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import java.util.List;
import static org.junit.Assert.*;

public class TestDBUsersServiceTest extends BaseServiceTest{
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private TestDBUsersService userService;

    @Before
    public void setupMock() {
        User user = new User();
        user.setEmail("first@email.com");
        User user2 = new User();
        user2.setEmail("second@email.com");
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);

        User user3 = new User();
        user3.setSalary(1500);
        user3.setRole(UserRole.ROLE_DRIVER);
        user3.setFullname("bla");
        user3.setEmail("bla");
        user3.setStatus(UserStatus.ACTIVE);

        User user4 = new User();
        user4.setSalary(2000);
        user4.setRole(UserRole.ROLE_COMPANY);
        user4.setFullname("bla");
        user4.setEmail("bla");
        user4.setStatus(UserStatus.ACTIVE);

        when(userRepository.findAll()).thenReturn(users);
        when(userRepository.save(user3)).thenReturn(user3);
        when(userRepository.save(user4)).thenReturn(null);
        when(userRepository.findOne(2L)).thenReturn(null);
        when(userRepository.findOne(5L)).thenReturn(user);
        when(userRepository.findByEmail(any(String.class))).thenReturn(user);
    }

    @Test
    public void getUsers() throws Exception {
        DBUsersResponse response = userService.getUsers();

        assertEquals(2, response.getTotalItems());
    }

    @Test
    public void getUsersActualData() throws Exception {
        DBUsersResponse response = userService.getUsers();

        User user = new User();
        user.setEmail("first@email.com");
        User user2 = new User();
        user2.setEmail("second@email.com");
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);

        assertEquals(users, response.getItems());
    }

    @Test
    public void getUsersFailed() throws Exception {
        DBUsersResponse response = userService.getUsers();

        assertNotEquals(0, response.getTotalItems());
    }

    @Test
    public void addNewItem() throws Exception {
        AddNewUserDto user3 = new AddNewUserDto();
        user3.setSalary(1500);
        user3.setRole("ROLE_DRIVER");
        user3.setFullname("bla");
        user3.setEmail("bla");
        user3.setStatus("ACTIVE");

        assertFalse(userService.addNewItem(user3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNewItemWithEmptyContent() throws Exception {
        AddNewUserDto user3 = new AddNewUserDto();
        user3.setSalary(0);
        user3.setRole("");
        user3.setFullname("");
        user3.setEmail("");
        user3.setStatus("");

        assertTrue(userService.addNewItem(user3));
    }

    @Test
    public void addNewItemFailed() throws Exception {
        AddNewUserDto user3 = new AddNewUserDto();
        user3.setSalary(2000);
        user3.setRole("ROLE_COMPANY");
        user3.setFullname("bla");
        user3.setEmail("bla");
        user3.setStatus("ACTIVE");

        assertTrue(userService.addNewItem(user3));
    }

    @Test
    public void updateUser() throws Exception {
        AddNewUserDto user3 = new AddNewUserDto();
        user3.setId(5L);
        user3.setSalary(2000);
        user3.setRole("ROLE_COMPANY");
        user3.setFullname("bla");
        user3.setEmail("bla");
        user3.setStatus("ACTIVE");

        userService.updateUser(user3);
    }

    @Test(expected = NullPointerException.class)
    public void updateUserFailed() throws Exception {
        AddNewUserDto user3 = new AddNewUserDto();
        user3.setId(2L);
        user3.setSalary(2000);
        user3.setRole("ROLE_COMPANY");
        user3.setFullname("bla");
        user3.setEmail("bla");
        user3.setStatus("ACTIVE");

        userService.updateUser(user3);
    }

    @Test
    public void deleteItem() throws Exception {
        userService.deleteItem(5L);
    }

}