package com.microservices.gateway.config;


import com.microservices.gateway.filter.JwtAuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final JwtAuthenticationFilter filter;

    public GatewayConfig(JwtAuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("soa-order-microservice", r -> r.path("/api/v1/order/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb:http://soa-order-microservice"))

                .route("soa-user-microservice", r -> r.path("/api/v1/user/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb:http://soa-user-microservice"))

                .route("soa-product-microservice", r -> r.path("/api/v1/product/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb:http://soa-product-microservice"))


                .route("soa-payment-microservice", r -> r.path("/api/v1/payment/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb:http://soa-payment-microservice"))

                .route("soa-auth-microservice", r -> r.path("/api/v1/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb:http://soa-auth-microservice"))

                .route("soa-order-detail-microservice", r -> r.path("/api/v1/order-detail/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb:http://soa-order-detail-microservice"))
                .build();
    }
}
