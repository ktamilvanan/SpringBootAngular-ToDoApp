package com.kathiravan.demo.security;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.kathiravan.demo.entity.User;


public interface UserService extends UserDetailsService {
	//May add a method to register user account
	public AuthenticatedUser registerNewAccount(User user);

}
