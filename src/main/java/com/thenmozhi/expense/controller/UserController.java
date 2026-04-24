package com.thenmozhi.expense.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thenmozhi.expense.entity.User;
import com.thenmozhi.expense.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController 
{
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public User signUp(@RequestBody User user)
	{
		//System.out.println("Name: "+user.getName()+",Email: "+user.getPassword()+",Password: "+user.getPassword());
		return userService.saveUser(user);
	}
	
	@GetMapping
	public List<User> getUsers()
	{
		return userService.getAllUsers();
	}
	
	@PostMapping("/login")
	public User login(@RequestBody User user)
	{
	    System.out.println("Email: " + user.getEmail());
	    System.out.println("Password: " + user.getPassword());

	    User u = userService.login(user.getEmail(), user.getPassword());

	    System.out.println("Result: " + u);

	    return u;
	}
}
