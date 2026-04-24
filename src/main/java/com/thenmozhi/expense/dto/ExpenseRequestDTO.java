package com.thenmozhi.expense.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ExpenseRequestDTO 
{
	@NotBlank(message = "Title cannot be empty")
	private String title;
	
	@Positive(message = "Amount must be greater than zero")
	private double amount;
	
	@NotBlank(message = "Category cannot be empty")
	private String category;
	
	@NotNull(message = "Date is required")
	private LocalDate date;
	
	@NotNull(message = "UserId is required")
	private Long userId;
}
