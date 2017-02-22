package com.kathiravan.demo.security;

import java.util.ArrayList;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kathiravan.demo.entity.ToDo;
import com.kathiravan.demo.entity.User;
import com.kathiravan.demo.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	public UserDetailsServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public AuthenticatedUser registerNewAccount(User user1) {
		//if email exists throw an error
		
		User user = new User();
		user.setFirstName(user1.getFirstName());
		user.setLastName(user1.getLastName());
		user.setEmail(user1.getEmail());
		user.setPassword(passwordEncoder.encode(user1.getPassword()));
		
		
		if(userRepository.save(user)!=null)
		{
			AuthenticatedUser auser = new AuthenticatedUser(user);
			Authentication auth = new UsernamePasswordAuthenticationToken(auser, null);
			
			SecurityContextHolder.getContext().setAuthentication(auth);
			return auser;
		}
		return null;
	}

	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		//if user is null throw an exception.
		User user = userRepository.findByEmail(arg0);
//		user.setTodos(new ArrayList<ToDo>());
		return new AuthenticatedUser(user);		
	}

}
