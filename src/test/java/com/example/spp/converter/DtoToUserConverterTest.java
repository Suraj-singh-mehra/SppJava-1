package com.example.spp.converter;

import com.example.spp.dto.AddNewUserDto;
import com.example.spp.models.User;
import com.example.spp.models.enums.UserRole;
import com.example.spp.models.enums.UserStatus;
import org.junit.Test;

import static org.junit.Assert.*;

public class DtoToUserConverterTest {
    @Test
    public void convertDtoToUser() throws Exception {
        AddNewUserDto dto = new AddNewUserDto();
        dto.setEmail("some@email.com");
        dto.setFullname("SomeName");
        dto.setRole("ROLE_USER");
        dto.setSalary(100);
        dto.setStatus("ACTIVE");

        User user = DtoToUserConverter.convertDtoToUser(dto);

        assertEquals("some@email.com", user.getEmail());
        assertEquals("SomeName", user.getFullname());
        assertEquals(UserRole.ROLE_USER, user.getRole());
        assertEquals(100, user.getSalary());
        assertEquals(UserStatus.ACTIVE, user.getStatus());
    }

}