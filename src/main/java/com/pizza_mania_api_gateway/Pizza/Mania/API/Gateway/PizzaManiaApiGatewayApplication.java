package com.pizza_mania_api_gateway.Pizza.Mania.API.Gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PizzaManiaApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaManiaApiGatewayApplication.class, args);
    }

    // Gateway Route Configuration
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()

                // ----------------- customer-service -----------------
                // Customer Service Route
                .route(r -> r.path("/api/v1/customers/**")
                        .filters(f -> f.addResponseHeader("X-Response-Header", "PizzaManiaApiGateway"))
                        .uri("http://localhost:8081"))

                // ----------------- admin-service -----------------
                // Admin Service Route
                .route(r -> r.path("/api/v1/admins/**")
                        .filters(f -> f.addResponseHeader("X-Response-Header", "PizzaManiaApiGateway"))
                        .uri("http://localhost:8081"))
                // ----------------- product-service -----------------
                .route(r -> r.path("/api/v1/products/**")
                        .filters(f -> f.addResponseHeader("X-Response-Header", "PizzaManiaApiGateway"))
                        .uri("http://localhost:8081"))

                // ----------------- notification-service -----------------
                .route(r -> r.path("/api/v1/notifications/**")
                        .filters(f -> f.addResponseHeader("X-Response-Header", "PizzaManiaApiGateway"))
                        .uri("http://localhost:3000"))

                .build();
    }
}
