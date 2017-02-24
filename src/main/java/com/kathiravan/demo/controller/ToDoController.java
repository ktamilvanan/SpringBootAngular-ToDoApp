package com.kathiravan.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kathiravan.demo.entity.ToDo;
import com.kathiravan.demo.entity.User;
import com.kathiravan.demo.repository.ToDoRepository;
import com.kathiravan.demo.repository.UserRepository;
import com.kathiravan.demo.security.SecurityUtils;

import io.swagger.annotations.Api;

@RestController
@Api(value = "todo")
public class ToDoController {

	@Autowired
	private ToDoRepository todoRepo;

	@Autowired
	private UserRepository userRepo;

	@RequestMapping(value = "/todos/greet", method = RequestMethod.GET)
	public String greet() {
		return "Hello World !";
	}

	@RequestMapping(value = "/todos", method = RequestMethod.GET)
	public List<ToDo> getToDos() {
		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		// AuthenticatedUser user1 = (AuthenticatedUser) auth.getPrincipal();

		User user = userRepo.findByEmail(SecurityUtils.getCurrentLogin());

		//This can be better done by a custom query.
		List<ToDo> todos = todoRepo.findByUser(user);
		for(ToDo todo : todos)
		{
			todo.setUser(null);
		}
		return todos;
	}

	@RequestMapping(value = "/todos", method = RequestMethod.POST)
	public ToDo create(ToDo todo) {
		User user = userRepo.findByEmail(SecurityUtils.getCurrentLogin());
		todo.setUser(user);
		todoRepo.saveAndFlush(todo);
		todo.setUser(null);
		return todo;
	}
	
	@RequestMapping(value = "/todos/{id}", method = RequestMethod.PATCH)
	public ToDo update(@PathVariable Long id, ToDo todo) {
		ToDo etodo = todoRepo.findOne(id);
		etodo.setDone(todo.isDone());
		etodo.setText(todo.getText());
		todoRepo.saveAndFlush(etodo);
		etodo.setUser(null);
		return etodo;
	}
}
