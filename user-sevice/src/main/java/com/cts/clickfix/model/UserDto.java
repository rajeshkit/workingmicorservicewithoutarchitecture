package com.cts.clickfix.model;

import java.util.List;

import com.cts.clickfix.entity.Roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private int userId;
	private String password;
	private String phone;
	private String email;
	private List<Roles> roles;

}
