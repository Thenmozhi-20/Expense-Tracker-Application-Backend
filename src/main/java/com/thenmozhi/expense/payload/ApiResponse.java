package com.thenmozhi.expense.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor  // it helps to write the constructor including all fields.
public class ApiResponse<T>
{
	private boolean status;
	private String message;
	private T data;
}

/**************************************************************
📌 Golden Rule (Remember This Always)
Generic type depends only on the field declared as <T>, not on all constructor parameters.
example : 
1.new ApiResponse<>(true,"OK",errors); --> return type = ApiResponse<Map<String,String>>

2. new ApiResponse<>(true,"OK",userList);--> return type= ApiResponse<List<User>>

3. new ApiResponse<>(true,"Saved",user); --> return type = ApiResponse<User>
*****************************************************************/