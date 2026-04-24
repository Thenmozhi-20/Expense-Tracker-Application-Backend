package com.thenmozhi.expense;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
public class Expense 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Title cannot by empty")
	private String title;
	
	@Positive(message = "Amount must be positive")
	private double amount;
	
	@NotBlank(message = "Categroy required")
	private String category;
	
	@NotNull(message = "Data required")
	private LocalDate date;
	
	@NotNull(message = "User id required")
	private Long userId;
}
