package com.example.spp.service;

import com.example.spp.dto.BiographyDto;
import com.example.spp.models.User;
import com.example.spp.models.enums.UserRole;
import com.example.spp.models.enums.UserStatus;
import com.example.spp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
    }

    public User create(String name, String password, String email) {
        User newUser = new User();

        newUser.setPassword(password);
        newUser.setFullname(name);
        newUser.setRole(UserRole.ROLE_USER);
        newUser.setEmail(email);
        newUser.setStatus(UserStatus.ACTIVE);
        return newUser;
    }

    public User findById(Long id) {
        return userRepository.findOne(id);
    }
}
