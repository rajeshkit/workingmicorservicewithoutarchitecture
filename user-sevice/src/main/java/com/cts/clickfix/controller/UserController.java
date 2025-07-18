package com.cts.clickfix.controller;

import org.springframework.web.bind.annotation.RestController;

import com.cts.clickfix.appconfig.JwtUtility;
import com.cts.clickfix.entity.User;
import com.cts.clickfix.model.LoginDto;
import com.cts.clickfix.model.UserDto;
import com.cts.clickfix.service.UserService;
import com.cts.clickfix.service.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/user-api")
@Slf4j
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtility jwtUtility;
	
	@PostMapping("/users")
	public UserDto addUser(@RequestBody UserDto userDto) {
		return userService.addUser(userDto);
	}
	//localhost:8081/user-api/users/cts.kit@gmail.com
	@GetMapping("/users/{email}")
	public UserDto getUserByEmail(@PathVariable("email") String email) {
		log.info("get user by email method is called");
		log.info("user email is"+email);
		return userService.getUserByEmail(email);
	}
	@PostMapping("/users/login")
	public String login(@RequestBody LoginDto login) {
		System.out.println("login called");
   	 Authentication authentication = authenticationManager.authenticate(
   	            new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
   	        if (authentication.isAuthenticated()) {
   	            return jwtUtility.generateToken(login.getUsername());
   	        } else {
   	            throw new UsernameNotFoundException("Invalid user email and password request!");
   	        }
		
		
	}

}
