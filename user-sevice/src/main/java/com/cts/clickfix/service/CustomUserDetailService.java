package com.cts.clickfix.service;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.CredentialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.clickfix.entity.Roles;
import com.cts.clickfix.entity.User;
import com.cts.clickfix.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByEmail(username);
		System.out.println("user data from db:"+user);
		if(user==null) {
			throw new UsernameNotFoundException("email is not there in the db !! check email");
		}
		List<GrantedAuthority> listAuthorities=new ArrayList<GrantedAuthority>();
		for (Roles role : user.getRoles()) {
			SimpleGrantedAuthority simpleGrantedAuthority=
					new SimpleGrantedAuthority("ROLE_"+role.getRoleName());
			System.out.println(simpleGrantedAuthority.getAuthority());
			listAuthorities.add(simpleGrantedAuthority);
		}
		org.springframework.security.core.userdetails.User u=
				new org.springframework.security.core.userdetails.User
				(user.getEmail(), user.getPassword(), true, true, true,true, listAuthorities);
		return u;
	}

	

}


