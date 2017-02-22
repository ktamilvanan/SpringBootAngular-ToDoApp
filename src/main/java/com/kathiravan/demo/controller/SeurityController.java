package com.kathiravan.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kathiravan.demo.entity.User;
import com.kathiravan.demo.repository.UserRepository;
import com.kathiravan.demo.security.AuthenticatedUser;
import com.kathiravan.demo.security.SecurityUtils;
import com.kathiravan.demo.security.UserService;

import io.swagger.annotations.Api;

@RestController
@Api(value="todo")
public class SeurityController {

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userService);
		authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		return authenticationProvider;
	}

    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/security/account", method = RequestMethod.GET)
    public @ResponseBody
    User getUserAccount()  {
        User user = userRepo.findByEmail(SecurityUtils.getCurrentLogin());
        user.setPassword(null);
        return user;
    }


    @RequestMapping(value="/security/register",method=RequestMethod.POST)
    public User registerUser(User user)
    {
    	AuthenticatedUser auser = userService.registerNewAccount(user);
    	user.setId(auser.getId());
    	return user;
    }



}