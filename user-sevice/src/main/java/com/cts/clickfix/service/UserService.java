package com.cts.clickfix.service;

import com.cts.clickfix.model.UserDto;

public interface UserService {
	public UserDto addUser(UserDto userDto);
	public UserDto getUserByEmail(String email);
}
