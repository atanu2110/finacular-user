package com.finadv.service;

import java.util.List;

import com.finadv.dto.UserExpenseList;
import com.finadv.dto.UserInvestmentList;
import com.finadv.entities.ExpenseCategory;
import com.finadv.entities.UserExpense;
import com.finadv.entities.UserInvestment;

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
	
	UserInvestmentList getUserInvestment(long userId);
	
	void createUserInvestment(UserInvestmentList userInvestmentList);
	
	UserInvestment updateUserInvestment(UserInvestment userInvestment);
	
	void deleteUserInvestment(long investmentId);
}
