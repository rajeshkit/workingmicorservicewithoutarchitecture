package com.cts.clickfix.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.clickfix.controller.UserController;
import com.cts.clickfix.entity.User;
import com.cts.clickfix.model.UserDto;
import com.cts.clickfix.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	
	  @Autowired 
	  private PasswordEncoder passwordEncoder;		
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

   
	@Override
	public UserDto addUser(UserDto userDto) {
		// repository save(UserEntity)
		System.out.println(userDto);
		userDto.setPassword(userDto.getPassword());
		User user = modelMapper.map(userDto, User.class);
		System.out.println(user);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User user1=userRepository.save(user);
		return modelMapper.map(user1, UserDto.class);
	}
	@Override
	public UserDto getUserByEmail(String email) {
		log.info("get by email called service methd");
	
		log.info("email is passed repo to get the user");
		User user = userRepository.findByEmail(email);
		if(user!=null) {
			log.info("user email found");
			return modelMapper.map(user, UserDto.class);
		}
		log.error("user email is not there in the db");
		throw new NullPointerException("user id is not found");
			
	}

}
