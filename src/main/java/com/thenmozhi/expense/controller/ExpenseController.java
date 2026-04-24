package com.thenmozhi.expense.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thenmozhi.expense.Expense;
import com.thenmozhi.expense.dto.ExpenseRequestDTO;
import com.thenmozhi.expense.payload.ApiResponse;
import com.thenmozhi.expense.service.ExpenseService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/expenses")
public class ExpenseController 
{
	@Autowired
	public ExpenseService expenseService;
	
	/*
	 1. getmapping - retrieving existing data
	 2. postmapping - creating a new resource 
	 3. putmapping - updating an existence resource
	 4. deletmapping - removing a resource
	 */
	
	
//	@PostMapping
//	public ApiResponse<Expense> addExpense(@Valid @RequestBody Expense expense)
//	{
//		ApiResponse<Expense> response = new ApiResponse<>(true,"Expense Saved Successfully!",service.saveExpense(expense));
//		return response;
//		
//		/**************************************************************
//		📌 Golden Rule (Remember This Always)
//		Generic type depends only on the field declared as <T>, not on all constructor parameters.
//		example : 
//		1.new ApiResponse<>(true,"OK",errors); --> return type = ApiResponse<Map<String,String>>
//
//		2. new ApiResponse<>(true,"OK",userList);--> return type= ApiResponse<List<User>>
//
//		3. new ApiResponse<>(true,"Saved",user); --> return type = ApiResponse<User>
//		*****************************************************************/
//	}
	
	@GetMapping
	public List<Expense> getExpenses()
	{
		return expenseService.getAllExpenses();
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<Expense>> saveExpense(@Valid @RequestBody ExpenseRequestDTO dto)
	{
		return ResponseEntity.ok(expenseService.saveExpense(dto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateExpense(@PathVariable Long id,@Valid @RequestBody ExpenseRequestDTO dto)
	{
		return ResponseEntity.ok(expenseService.updateExpense(id,dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteExpense(@PathVariable Long id)
	{
		return ResponseEntity.ok(expenseService.deleteExpense(id));
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<ApiResponse> getExpenseByUserId(@PathVariable Long userId)
	{
		List<Expense> expenses = expenseService.getExpenseByUserId(userId);
		
		ApiResponse response = new ApiResponse(true, "Expenses fetched successfully!",expenses);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<ApiResponse> getExpenseByCategory(@PathVariable String category)
	{
		List<Expense> expenses = expenseService.getExpenseByCategory(category);
		
		ApiResponse response = new ApiResponse(true , "Expenses fetched successfully!",expenses);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/total")
	public ResponseEntity<ApiResponse> getTotalExpense()
	{
		Double total= expenseService.getTotalExpense();
		
		ApiResponse response = new ApiResponse(true, "Total Expense calculated successfully!", total);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/date-range")
	public List<Expense> getExpenseByDateBetween(@RequestParam LocalDate start, @RequestParam LocalDate end)
	{
		return expenseService.getExpenseDateRange(start, end);
	}
	
	/* When dealing with large amounts of data, loading everything at once can be slow and inefficient. Pagination helps 
	by dividing data into smaller parts or “pages” that load faster. Sorting lets you arrange the data in order 
	(like alphabetically or by number), making it easier to find what you need.

	Where to use pagination and sorting?
	These techniques are commonly used in web apps that show lists or tables, like product catalogs, search results, 
	or user lists. Pagination breaks data into manageable pages, and sorting helps users quickly find specific information.*/
	
	@GetMapping("/pagination")
	public ResponseEntity<Page<Expense>> getExpenseWithPagination(@RequestParam int page,@RequestParam int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		Page<Expense> expenses = expenseService.getExpenseWithPagination(pageable);
		
		return ResponseEntity.ok(expenses);
	}
	
	@GetMapping("/category-summary")
	public ResponseEntity<List<Object[]>> getCategorySummary()
	{
		return ResponseEntity.ok(expenseService.getCategorySummary());
	}
	
	@GetMapping("/monthly-summary")
	public ResponseEntity<List<Object[]>> getMonthlySummary()
	{
		return ResponseEntity.ok(expenseService.getMonthSummary());
	}
}
