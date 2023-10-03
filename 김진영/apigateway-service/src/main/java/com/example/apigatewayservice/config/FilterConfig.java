package com.example.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {
    //@Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r.path("/first-service/**") //path 적용해서 (first-service가 들어오면)
                        .filters(f -> f.addRequestHeader("first-request","first-request-header")
                                        .addResponseHeader("first-response","first-response-header")) //filter 통해
                        .uri("http://localhost:8081")) //uri 전달 (해당 uri로 이동)
                .route(r -> r.path("/second-service/**") //path 적용해서 (second-service가 들어오면)
                        .filters(f -> f.addRequestHeader("second-request","second-request-header")
                                .addResponseHeader("second-response","second-response-header")) //filter 통해
                        .uri("http://localhost:8082")) //uri 전달 (해당 uri로 이동)
                .build();
    }
}
