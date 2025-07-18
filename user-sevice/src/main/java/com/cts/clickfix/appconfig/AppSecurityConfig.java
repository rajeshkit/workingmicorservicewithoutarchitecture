package com.cts.clickfix.appconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeHttpRequests(auth -> auth.requestMatchers("/user-api/users","/user-api/users/{email}", "/user-api/users/login")
				.permitAll()
//				.requestMatchers(HttpMethod.GET, "/mechanic-api/mechanics/{mechanicId}").hasRole("USER")
//				.requestMatchers("/mechanic-api/**").hasRole("ADMIN")
				.anyRequest().authenticated());
		httpSecurity.csrf(csrf -> csrf.disable());
		httpSecurity.cors(cors -> cors.disable());
		httpSecurity.addFilterBefore(jwtFilter, BasicAuthenticationFilter.class);
		httpSecurity.httpBasic(Customizer.withDefaults());
		return httpSecurity.build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
