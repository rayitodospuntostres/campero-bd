package com.microservices.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SoaUserMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoaUserMicroserviceApplication.class, args);
    }

}
