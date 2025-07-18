package com.cts.clickfix.appconfig;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.clickfix.model.BookingDto;
import com.cts.clickfix.model.UserDto;

@FeignClient(name = "USER-SERVICE")
public interface UserFeignClient {
	@GetMapping("/user-api/users/{userEmail}")
	public UserDto findUser(@PathVariable String userEmail);
}
//UserDto response=restTemplate.getForObject("http://USER-SERVICE/user-api/users/"+userEmail,UserDto.class);
//System.out.println(response);
