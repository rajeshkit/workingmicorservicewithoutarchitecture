package com.cts.clickfix.controller;

import org.springframework.web.bind.annotation.RestController;

import com.cts.clickfix.model.UserDto;
import com.cts.clickfix.service.UserService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String login() {
		System.out.println("login called");
   		return "hello";
		
		
	}

}
