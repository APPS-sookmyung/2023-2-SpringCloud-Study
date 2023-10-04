package com.example.apigatewayservice.common.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

//@Configuration
public class FilterConfig {
    //    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(
                        r -> r.path("/first-service/**") // 1. 경로 확인
                                .filters(filter -> filter // 2. filter 적용
                                        .addRequestHeader("first-request", "first-request-header")
                                        .addResponseHeader("first-response", "first-response-header"))
                                .uri("http://localhost:8081")) // 3. url 이동
                .route(
                        r -> r.path("/second-service/**")
                                .filters(filter -> filter
                                        .addRequestHeader("second-request", "second-request-header")
                                        .addResponseHeader("second-response", "second-response-header"))
                                .uri("http://localhost:8082"))
                .build();
    }
}
