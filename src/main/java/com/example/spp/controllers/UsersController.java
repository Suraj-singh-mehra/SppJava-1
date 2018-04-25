package com.example.spp.controllers;

import com.example.spp.dto.AddNewUserDto;
import com.example.spp.dto.BiographyDto;
import com.example.spp.dto.ChangeUserDto;
import com.example.spp.models.User;
import com.example.spp.rest.DBUsersResponse;
import com.example.spp.service.AuthenticationService;
import com.example.spp.service.TestDBUsersService;
import com.example.spp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {
    @Autowired
    private TestDBUsersService testDBUsersService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/getAllDataFromUsers", method = RequestMethod.GET)
    public List<User> getUsers(@RequestParam(name = "_page") int page, @RequestParam(name = "_limit") int limit) {
        DBUsersResponse response = testDBUsersService.getUsers();
        return response.getItems();
    }

    @RequestMapping(value = "/addNewUser", method = RequestMethod.POST)
    public boolean addNewUser(@Validated @RequestBody AddNewUserDto newUserDto) {
        return testDBUsersService.addNewItem(newUserDto);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public void updateUser(@Validated @RequestBody AddNewUserDto newUserDto) {
        testDBUsersService.updateUser(newUserDto);
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") Long id) {
        testDBUsersService.deleteItem(id);
    }

    @GetMapping(value = "/user/user-info/{id}")
    public BiographyDto getUserInfo(@PathVariable Long id) {
        return convertToBiographyDto(userService.findById(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping(value = "/user/user-info/save-changes")
    @ResponseStatus(value = HttpStatus.OK)
    public boolean getUserInfo(@RequestBody final ChangeUserDto changeUserDto) {
        authenticationService.checkUser(changeUserDto);
        return true;
    }

    private BiographyDto convertToBiographyDto(User user) {
        BiographyDto biographyDto = new BiographyDto();
        biographyDto.setBiography(user.getBiography());
        biographyDto.setSalary(user.getSalary());
        biographyDto.setLocation(user.getLocation());
        biographyDto.setName(user.getFullname());
        return biographyDto;
    }
}
