package com.microservices.auth.client;

import com.microservices.auth.dto.RegisterDto;
import com.microservices.auth.dto.UserDto;
import com.microservices.auth.request.RegisterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "soa-user-microservice",path = "/api/v1/user/")
public interface UserServiceClient {

    @PostMapping("/save")
    ResponseEntity<RegisterDto> save(@RequestBody RegisterRequest registerRequest);

    @GetMapping("/getUserByUsername/{username}")
    ResponseEntity<UserDto> getUserByUsername(@PathVariable String username);
}
