package com.cts.clickfix.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String password;
	private String phone;
	@Column(nullable = false,unique = true)
	private String email;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Roles> roles;
	
}
