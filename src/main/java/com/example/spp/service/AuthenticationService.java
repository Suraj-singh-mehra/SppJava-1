package com.example.spp.service;

import com.example.spp.dto.AuthUserDto;
import com.example.spp.dto.ChangeUserDto;
import com.example.spp.dto.login.LoginRequestDto;
import com.example.spp.dto.login.LoginResponseDto;
import com.example.spp.dto.registration.RegistrationRequestDto;
import com.example.spp.dto.registration.RegistrationResponseDto;
import com.example.spp.models.User;
import com.example.spp.repository.UserRepository;
import com.example.spp.security.SecurityHelper;
import com.example.spp.security.exception.JsonException;
import com.example.spp.security.model.AuthUserTransformer;
import com.example.spp.security.model.JwtUserDetails;
import com.example.spp.security.service.AuthenticationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.authentication.AuthenticationManager;


import java.util.Objects;
import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthUserTransformer authUserTransformer;
    @Autowired
    private AuthenticationHelper authenticationHelper;
    @Autowired
    private AuthenticationManager authenticationManager;

    public LoginResponseDto login(final LoginRequestDto loginRequestDto) {
        try {
            String email = Optional.ofNullable(loginRequestDto.getUsername())
                    .orElseThrow(() -> new BadCredentialsException("Username should be passed."));

            String password = Optional.ofNullable(loginRequestDto.getPassword())
                    .orElseThrow(() -> new BadCredentialsException("Password should be passed."));

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email,
                    password);

            // Try to authenticate with this token
            final Authentication authResult = this.authenticationManager.authenticate(authRequest);

            // Set generated JWT token to response header
            if (authResult.isAuthenticated()) {
                JwtUserDetails userDetails = (JwtUserDetails) authResult.getPrincipal();

                User user = userRepository.findOne(userDetails.getId());
                if (Objects.isNull(user)) {
                    throw new JsonException("User not exist in system.");
                }

                String token = this.authenticationHelper.generateToken(userDetails.getId());
                User newUser = userRepository.findByEmail(loginRequestDto.getUsername());
                userRepository.save(newUser);
                return new LoginResponseDto(token);
            } else {
                throw new JsonException("Authentication failed.");
            }
        } catch (BadCredentialsException exception) {
            throw new JsonException("Username or password was incorrect. Please try again.", exception);
        }
    }

    /**
     * Get user info.
     * @return user info.
     */
    @Transactional(readOnly = true)
    public AuthUserDto getMe() {
        Authentication authentication = SecurityHelper.getAuthenticationWithCheck();
        User byUsername = userRepository.findByEmail(authentication.getName());
        return authUserTransformer.makeDto(byUsername);
    }

    public RegistrationResponseDto register(
            RegistrationRequestDto registrationRequestDto,
            String appUrl
    ) {
        try {
            String username = Optional.ofNullable(registrationRequestDto.getUsername())
                    .orElseThrow(() -> new BadCredentialsException("Username should be passed."));

            String password = Optional.ofNullable(registrationRequestDto.getPassword())
                    .orElseThrow(() -> new BadCredentialsException("Password should be passed."));

            String email = Optional.ofNullable(registrationRequestDto.getEmail())
                    .orElseThrow(() -> new BadCredentialsException("Email should be passed."));

            User user = userRepository.findByEmail(email);
            if (!Objects.isNull(user)) {
                throw new JsonException("Email is already in use.");
            }

            User newUser = userService.create(username, password, email);
            userService.save(newUser);

            String token = this.authenticationHelper.
                    generateToken(userRepository.findByEmail(newUser.getEmail()).getId());

            return new RegistrationResponseDto(token);
        } catch (BadCredentialsException exception) {
            throw new JsonException("Unable to register. Please try again.", exception);
        }
    }

    public void checkUser(ChangeUserDto changeUserDto) {

        try {
            String email = Optional.ofNullable(changeUserDto.getEmail())
                    .orElseThrow(() -> new BadCredentialsException("Email should be passed."));

            String password = Optional.ofNullable(changeUserDto.getPassword())
                    .orElseThrow(() -> new BadCredentialsException("Password should be passed."));

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email,
                    password);

            // Try to authenticate with this token
            final Authentication authResult = this.authenticationManager.authenticate(authRequest);

            // Set generated JWT token to response header
            if (authResult.isAuthenticated()) {
                JwtUserDetails userDetails = (JwtUserDetails) authResult.getPrincipal();

                User user = userRepository.findOne(userDetails.getId());
                if (Objects.isNull(user)) {
                    throw new JsonException("User not exist in system.");
                }
            } else {
                throw new JsonException("Authentication failed.");
            }

            User biography = userRepository.findByEmail(email);
            biography.setBiography(changeUserDto.getBiography());
            biography.setLocation(changeUserDto.getLocation());
            biography.setSalary(Integer.parseInt(changeUserDto.getSalary()));
            biography.setFullname(changeUserDto.getName());
            userRepository.save(biography);
        } catch (BadCredentialsException exception) {
            throw new JsonException("Username or password was incorrect. Please try again.", exception);
        }
    }
}
