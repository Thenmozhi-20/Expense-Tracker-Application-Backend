package com.thenmozhi.expense.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thenmozhi.expense.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long>
{
	List<Expense> findByUserId(Long userId);
	List<Expense> findByCategory(String category);
	List<Expense> findByDateBetween(LocalDate start,LocalDate end);
	
	@Query("SELECT SUM(e.amount) FROM Expense e")
	Double getTotalExpense();
	
	Page<Expense> findAll(Pageable pageable); // for pagination
	
	// this shows - How much money was spent in each category
	@Query("SELECT e.category, SUM(e.amount) FROM Expense e GROUP BY e.category")
	List<Object[]> getCategorySummary();
	
	// This shows how much money you spent each month.
	@Query("SELECT MONTH(e.date),SUM(e.amount) FROM Expense e GROUP BY MONTH(e.date)")
	List<Object[]> getMonthSummary();
	
}
