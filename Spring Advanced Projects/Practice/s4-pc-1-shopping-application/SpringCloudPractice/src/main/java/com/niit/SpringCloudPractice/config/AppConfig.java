package com.niit.SpringCloudPractice.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class AppConfig {



    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/api/v2/**")
                        .uri("lb://customer-authentication-service"))

                 .route(p->p
                        .path("/api/v1/**")
                        .uri("lb://customer-product-service"))
                .build();
    }

}


