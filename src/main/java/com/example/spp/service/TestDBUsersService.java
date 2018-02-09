package com.example.spp.service;

import com.example.spp.converter.DtoToUserConverter;
import com.example.spp.dto.AddNewUserDto;
import com.example.spp.models.User;
import com.example.spp.models.enums.UserStatus;
import com.example.spp.repository.UserRepository;
import com.example.spp.rest.DBUsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TestDBUsersService {

    @Autowired
    private UserRepository userRepository;

    public DBUsersResponse getUsers() {
        DBUsersResponse response = new DBUsersResponse();
        List<User> users = userRepository.findAll();
        response.setItems(users);
        response.setTotalItems(users.size());
        return response;
    }

    public boolean addNewItem(AddNewUserDto newUserDto) {
        boolean result = true;
        User savedItem = userRepository.save(DtoToUserConverter.convertDtoToUser(newUserDto));
        if (savedItem != null) {
            result = false;
        }
        return result;
    }

    public void updateUser(AddNewUserDto newUserDto) {
        User user = userRepository.findOne(newUserDto.getId());
        user.setEmail(newUserDto.getEmail());
        user.setFullname(newUserDto.getFullname());
        user.setSalary(newUserDto.getSalary());
        user.setStatus(UserStatus.valueOf(newUserDto.getStatus()));
        userRepository.save(user);
    }

    public void deleteItem(Long id) {
        userRepository.delete(id);
    }
}
