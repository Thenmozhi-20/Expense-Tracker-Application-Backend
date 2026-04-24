package com.thenmozhi.expense.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thenmozhi.expense.entity.User;
import com.thenmozhi.expense.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController 
{
	private final UserRepository userRepo;
	
	public AuthController(UserRepository userRepo)
	{
		this.userRepo = userRepo;
	}
	
	@PostMapping("/register")
	public User register(@RequestBody User user)
	{
		return userRepo.save(user);
	}
}
