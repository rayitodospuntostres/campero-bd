package com.microservices.auth.controllers;

import com.microservices.auth.dto.RegisterDto;
import com.microservices.auth.dto.TokenDto;
import com.microservices.auth.request.LoginRequest;
import com.microservices.auth.request.RegisterRequest;
import com.microservices.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequest request) {
        logger.info("Logging in user: {}", request.getUsername());
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/save")
    public ResponseEntity<RegisterDto> save(@RequestBody RegisterRequest request) {
        logger.info("Registering user: {}", request.getUsername());
        return ResponseEntity.ok(authService.save(request));
    }
}
