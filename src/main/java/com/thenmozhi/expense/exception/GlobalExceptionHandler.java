package com.thenmozhi.expense.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.thenmozhi.expense.payload.ApiResponse;

// The @RestControllerAdvice annotation in Spring Boot is used to define a global exception handler for all @RestController classes.
//  It acts as an intercepter, catching exceptions thrown by controllers and converting them into structured JSON error responses.

@RestControllerAdvice  //  @ControllerAdvice + @ResponseBody
public class GlobalExceptionHandler
{
	@ExceptionHandler(MethodArgumentNotValidException.class)  // MethodArgumentNotValidException --> MethodArgumentNotValidException is spring class
	public ApiResponse<Map<String,String>> handleValidation(MethodArgumentNotValidException ex)
	{
		Map<String,String> errors = new HashMap<>();
		
		for(FieldError error : ex.getBindingResult().getFieldErrors())
		{
			errors.put(error.getField(),error.getDefaultMessage());
		}
		
		ApiResponse<Map<String, String>> response = new ApiResponse<>(false,"Validation Failed!",errors);
	    return response;
	    
	    /**************************************************************
	    📌 Golden Rule (Remember This Always)
	    Generic type depends only on the field declared as <T>, not on all constructor parameters.
	    example : 
	    1.new ApiResponse<>(true,"OK",errors); --> return type = ApiResponse<Map<String,String>>

	    2. new ApiResponse<>(true,"OK",userList);--> return type= ApiResponse<List<User>>

	    3. new ApiResponse<>(true,"Saved",user); --> return type = ApiResponse<User>
	    *****************************************************************/
	}
	
	@ExceptionHandler(ResourceNotFoundException.class) // ResourceNotFoundException is user created (present in same package)
	public ApiResponse<String> handleNotFound(ResourceNotFoundException ex)
	{
		ApiResponse<String> response = new ApiResponse<>(false,ex.getMessage(),null);
		return response;
	}
}
