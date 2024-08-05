package com.bej.springcloudapigateway.config;


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
                        .path("/api/v1/**")
                        .uri("http://user-authentication-service:8086/")) // use the name of the application in the uri

                 .route(p->p
                        .path("/api/v2/**")
                        .uri("http://user-movie-service:8081/"))
                .build();
    }

}


