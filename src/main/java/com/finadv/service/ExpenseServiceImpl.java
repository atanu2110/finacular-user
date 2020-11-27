package com.finadv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finadv.dto.UserExpenseList;
import com.finadv.entities.ExpenseCategory;
import com.finadv.entities.UserExpense;
import com.finadv.repository.ExpenseCategoryRepository;
import com.finadv.repository.ExpenseRepository;

/**
 * @author atanu
 *
 */
@Service
public class ExpenseServiceImpl implements ExpenseService {
	private ExpenseCategoryRepository categoryRepository;

	private ExpenseRepository expenseRepository;

	@Autowired
	public void setCategoryRepository(ExpenseCategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Autowired
	public void setExpenseRepository(ExpenseRepository expenseRepository) {
		this.expenseRepository = expenseRepository;
	}

	/**
	 *
	 */
	@Override
	public List<ExpenseCategory> getAllExpenseCatrgory() {
		return categoryRepository.findAll();
	}

	/**
	 *
	 */
	@Override
	public UserExpenseList getUserExpenses(long userId) {
		UserExpenseList userExpenseList = new UserExpenseList();
		List<UserExpense> expenseList = expenseRepository.findUserExpensesByUserId(userId);
		userExpenseList.setExpenseList(expenseList);
		return userExpenseList;
	}

	@Override
	public void createUserExpense(UserExpenseList userExpense) {
		expenseRepository.saveAll(userExpense.getExpenseList());
	}

	@Override
	public UserExpense updateUserExpense(UserExpense userExpense) {

		UserExpense userExpenseInDB = expenseRepository.findById(userExpense.getExpenseId()).orElse(null);
		if (userExpenseInDB != null) {
			expenseRepository.save(userExpense);
			return userExpense;
		}

		return null;
	}

	@Override
	public void deleteUserExpense(long expenseId) {
		expenseRepository.deleteById((int) expenseId);

	}

}
