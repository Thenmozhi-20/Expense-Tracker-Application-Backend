package com.thenmozhi.expense.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thenmozhi.expense.Expense;
import com.thenmozhi.expense.dto.ExpenseRequestDTO;
import com.thenmozhi.expense.exception.ResourceNotFoundException;
import com.thenmozhi.expense.payload.ApiResponse;
import com.thenmozhi.expense.repository.ExpenseRepository;
import com.thenmozhi.expense.repository.UserRepository;
import com.thenmozhi.expense.entity.User;


@Service
public class ExpenseService 
{
	@Autowired
	private ExpenseRepository expenseRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired 
	private UserService serv; 
	
	public ApiResponse<Expense> saveExpense(ExpenseRequestDTO dto)
	{
		serv.getUserById(dto.getUserId());
		
		Expense expense = new Expense();
		
		expense.setTitle(dto.getTitle());
		expense.setAmount(dto.getAmount());
		expense.setCategory(dto.getCategory());
		expense.setDate(dto.getDate());
		expense.setUserId(dto.getUserId());
		
		Expense saved = expenseRepo.save(expense);
		
		ApiResponse<Expense> apiResp = new ApiResponse<>(true,"Expense Saved Successfully!",saved);
		return apiResp;
	}
	
	public List<Expense> getAllExpenses()
	{
		return expenseRepo.findAll();  
		// findAll is predefined method.--> return type is List<Expense>
	}
	
	public ApiResponse<Expense> updateExpense(Long id,ExpenseRequestDTO dto)
	{
		Expense expense = expenseRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Expense not found with id "+ id));
		
		User user = userRepo.findById(dto.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not found with id "+dto.getUserId()));
		
		expense.setTitle(dto.getTitle());
		expense.setAmount(dto.getAmount());
		expense.setCategory(dto.getCategory());
		expense.setDate(dto.getDate());
		expense.setUserId(dto.getUserId());
		
		expenseRepo.save(expense);
		
		return new ApiResponse<Expense>(true, "Expense updated successfully!",expense);
	}
	
	public ApiResponse<Expense> deleteExpense(Long id)
	{
		Expense expense = expenseRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Expense not found with id: "+id));
		
		expenseRepo.delete(expense);
		
		return new ApiResponse<Expense>(true,"Expense deleted succesfully!",null);
	}
	
	public List<Expense> getExpenseByUserId(Long userId)
	{
		return expenseRepo.findByUserId(userId);
	}
	
	public List<Expense> getExpenseByCategory(String category)
	{
		return expenseRepo.findByCategory(category);
	}
	
	public Double getTotalExpense()
	{
		return expenseRepo.getTotalExpense();
	}
	
	public List<Expense> getExpenseDateRange(LocalDate start,LocalDate end)
	{
		return expenseRepo.findByDateBetween(start, end);
	}
	
	public Page<Expense> getExpenseWithPagination(Pageable pageable)
	{
		return expenseRepo.findAll(pageable);
	}
	
	// Category Summary (Analytics) - How much money was spent in each category
	public List<Object[]> getCategorySummary()
	{
		return expenseRepo.getCategorySummary();
	}
	
	// This shows how much money you spent each month
	public List<Object[]> getMonthSummary()
	{
		return expenseRepo.getMonthSummary();
	}
}
