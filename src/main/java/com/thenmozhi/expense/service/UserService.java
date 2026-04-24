package com.thenmozhi.expense.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thenmozhi.expense.entity.User;
import com.thenmozhi.expense.exception.ResourceNotFoundException;
import com.thenmozhi.expense.repository.UserRepository;

@Service
public class UserService 
{
	@Autowired
	private UserRepository repo;
	
	// save user
	public User saveUser(User user)
	{
		return repo.save(user); 
		// save ==>  org.springframework.data.repository.CrudRepository.save 
	}
	
	// get all users
	public List<User> getAllUsers()
	{
		return repo.findAll();
	}
	
	public User getUserById(Long id)
	{
		return repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found with id" + id));
	}

	public User login(String email, String password) {
	    return repo.findByEmailAndPassword(email, password);
	}
}
