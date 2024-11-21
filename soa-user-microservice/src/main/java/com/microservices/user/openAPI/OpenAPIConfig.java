package com.microservices.user.openAPI;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;


import java.util.List;

@OpenAPIDefinition
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(new Server().url("http://localhost:8099")))
                .info(new Info()
                        .title("User Service API")
                        .description("User Service API Specs")
                        .version("1.0.0"));
    }
}
