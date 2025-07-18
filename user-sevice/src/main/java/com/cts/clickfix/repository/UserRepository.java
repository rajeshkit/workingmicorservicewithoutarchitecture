package com.cts.clickfix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.clickfix.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByEmail(String email);
}
