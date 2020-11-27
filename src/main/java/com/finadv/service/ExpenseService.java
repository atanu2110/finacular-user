package com.finadv.service;

import java.util.List;

import com.finadv.dto.UserExpenseList;
import com.finadv.entities.ExpenseCategory;
import com.finadv.entities.UserExpense;

/**
 * @author atanu
 *
 */
public interface ExpenseService {

	List<ExpenseCategory> getAllExpenseCatrgory();
	
	UserExpenseList getUserExpenses(long userId);
	
	void createUserExpense(UserExpenseList userExpenseList);
	
	UserExpense updateUserExpense(UserExpense userExpense);
	
	void deleteUserExpense(long expenseId);
}
