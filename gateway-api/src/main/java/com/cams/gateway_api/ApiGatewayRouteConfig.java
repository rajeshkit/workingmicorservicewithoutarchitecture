package com.cams.gateway_api;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayRouteConfig {
    @Bean
    RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("USER-SERVICE",
                        r -> r.path("/user-api/**").uri("lb://USER-SERVICE"))
                .route("MECHANIC-SERVICE",
                        r -> r.path("/mechanic-api/**").uri("lb://MECHANIC-SERVICE"))
                .route("BOOKING-SERVICE",
                        r -> r.path("/booking-api/**").uri("lb://BOOKING-SERVICE"))
                .build();
    }

}
