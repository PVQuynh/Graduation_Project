package com.example.gateway_server.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteLocatorConfig {
    @Bean
    public RouteLocator gatewayConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/app/users/**")
                        .filters( f -> f.rewritePath("/app/users/(?<segment>.*)","/${segment}"))
                        .uri("lb://USERSERVER"))
                .route(p -> p
                        .path("/app/data/**")
                        .uri("lb://DATASERVER"))
                .route(p -> p
                        .path("/app/chat/**")
                        .uri("lb://CHATSERVER")).build();
    }
}
