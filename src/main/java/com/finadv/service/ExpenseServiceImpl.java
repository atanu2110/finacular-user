package com.finadv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finadv.dto.UserExpenseList;
import com.finadv.dto.UserInvestmentList;
import com.finadv.entities.ExpenseCategory;
import com.finadv.entities.UserExpense;
import com.finadv.entities.UserInvestment;
import com.finadv.repository.ExpenseCategoryRepository;
import com.finadv.repository.ExpenseRepository;
import com.finadv.repository.InvestmentRepository;

/**
 * @author atanu
 *
 */
@Service
public class ExpenseServiceImpl implements ExpenseService {
	private ExpenseCategoryRepository categoryRepository;

	private ExpenseRepository expenseRepository;

	private InvestmentRepository investmentRepository;

	@Autowired
	public void setCategoryRepository(ExpenseCategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Autowired
	public void setExpenseRepository(ExpenseRepository expenseRepository) {
		this.expenseRepository = expenseRepository;
	}

	@Autowired
	public void setInvestmentRepository(InvestmentRepository investmentRepository) {
		this.investmentRepository = investmentRepository;
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

	@Override
	public UserInvestmentList getUserInvestment(long userId) {

		UserInvestmentList userInvestmentList = new UserInvestmentList();
		List<UserInvestment> investList = investmentRepository.findUserInvestmentByUserId(userId);
		userInvestmentList.setInvestmentList(investList);
		return userInvestmentList;
	}

	@Override
	public void createUserInvestment(UserInvestmentList userInvestmentList) {
		investmentRepository.saveAll(userInvestmentList.getInvestmentList());	
	}

	@Override
	public UserInvestment updateUserInvestment(UserInvestment userInvestment) {
		UserInvestment userInvestmentInDB = investmentRepository.findById(userInvestment.getInvestmentId()).orElse(null);
		if (userInvestmentInDB != null) {
			investmentRepository.save(userInvestment);
			return userInvestment;
		}
		return null;
	}

	@Override
	public void deleteUserInvestment(long investmentId) {
		investmentRepository.deleteById((int) investmentId);
	}

}
