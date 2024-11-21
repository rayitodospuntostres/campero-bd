package com.microservices.auth.service;

import com.microservices.auth.client.UserServiceClient;
import com.microservices.auth.dto.RegisterDto;
import com.microservices.auth.dto.TokenDto;
import com.microservices.auth.dto.UserDto;
import com.microservices.auth.exceptions.WrongCredentialsException;
import com.microservices.auth.request.LoginRequest;
import com.microservices.auth.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final AuthenticationManager authenticationManager;
    private final UserServiceClient userServiceClient;
    private final JwtService jwtService;

    /**
     * Authenticates a user and generates a JWT token.
     *
     * @param request the login request containing username and password
     * @return TokenDto containing the JWT token
     * @throws WrongCredentialsException if authentication fails
     */
    public TokenDto login(LoginRequest request) {

        if (!userExists(request.getUsername())) {
            logger.error("Login failed: User {} not found", request.getUsername());
            throw new UsernameNotFoundException("User not found");
        }

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        if (authenticate.isAuthenticated()) {
            logger.info("User {} authenticated successfully", request.getUsername());
            return TokenDto.builder()
                    .token(jwtService.generateToken(request.getUsername()))
                    .build();
        } else {
            logger.error("Authentication failed for user {}", request.getUsername());
            throw new WrongCredentialsException("Wrong credentials");
        }
    }

    /**
     * Registers a new user after validating that the user does not already exist.
     *
     * @param request the registration request containing user details
     * @return RegisterDto containing the registered user details
     * @throws IllegalStateException if the user already exists
     */
    public RegisterDto save(RegisterRequest request) {

        return userServiceClient.save(request).getBody();
    }

    /**
     * Checks if a user exists by username using the UserServiceClient.
     *
     * @param username the username to check
     * @return true if the user exists, false otherwise
     */
    private boolean userExists(String username) {
        try {
            ResponseEntity<UserDto> response = userServiceClient.getUserByUsername(username);
            boolean exists = response.getStatusCode().is2xxSuccessful();
            logger.info("User existence check for {}: {}", username, exists);
            return exists;
        } catch (Exception e) {
            logger.error("Error during user existence check for {}: {}", username, e.getMessage());
            return false; // Default to false if an error occurs
        }
    }
}
