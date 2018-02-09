package com.example.spp.converter;

import com.example.spp.dto.AddNewUserDto;
import com.example.spp.models.User;
import com.example.spp.models.enums.UserRole;
import com.example.spp.models.enums.UserStatus;

public class DtoToUserConverter {
    public static User convertDtoToUser(AddNewUserDto newUserDto) {
        User user = new User();
        user.setSalary(newUserDto.getSalary());
        user.setRole(UserRole.valueOf(newUserDto.getRole()));
        user.setFullname(newUserDto.getFullname());
        user.setEmail(newUserDto.getEmail());
        user.setStatus(UserStatus.valueOf(newUserDto.getStatus()));
        return user;
    }
}
