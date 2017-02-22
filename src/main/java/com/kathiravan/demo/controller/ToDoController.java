package com.kathiravan.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kathiravan.demo.entity.ToDo;
import com.kathiravan.demo.entity.User;
import com.kathiravan.demo.repository.ToDoRepository;

import io.swagger.annotations.Api;

@RestController
@Api(value="todo")
public class ToDoController {

	@Autowired
	private ToDoRepository todoRepo;
	
	@RequestMapping(value="/todos/greet",method=RequestMethod.GET)
	public String greet()
	{
		return "Hello World !";
	}
	
	@RequestMapping(value="/todos",method=RequestMethod.GET)
	public List<ToDo> getToDos(@AuthenticationPrincipal User user)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails user1 = (UserDetails) auth.getPrincipal();
		
		return todoRepo.findByUser(user);
	}
	
	@RequestMapping(value="/todos",method=RequestMethod.POST)
	public ToDo create(@RequestBody ToDo todo)
	{
		return todoRepo.saveAndFlush(todo);
	}
}
