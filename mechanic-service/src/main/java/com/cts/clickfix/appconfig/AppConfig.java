package com.cts.clickfix.appconfig;

import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
