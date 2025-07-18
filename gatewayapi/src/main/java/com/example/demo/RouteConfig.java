package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {
	@Autowired
	private AuthenticationFilter filter;

	@Bean
	RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("USER-SERVICE",
						r -> r.path("/user-api/**").filters(f -> f.filter(filter)).uri("lb://USER-SERVICE"))
				.route("MECHANIC-SERVICE",
						r -> r.path("/mechanic-api/**").filters(f -> f.filter(filter)).uri("lb://MECHANIC-SERVICE"))
				.route("BOOKING-SERVICE",
						r -> r.path("/booking-api/**").filters(f -> f.filter(filter)).uri("lb://BOOKING-SERVICE"))
				.build();
	}

}
