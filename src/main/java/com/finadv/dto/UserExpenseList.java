package com.finadv.dto;

import java.util.List;

import com.finadv.entities.UserExpense;

/**
 * @author atanu
 *
 */
public class UserExpenseList {

	private List<UserExpense> expenseList;

	public List<UserExpense> getExpenseList() {
		return expenseList;
	}

	public void setExpenseList(List<UserExpense> expenseList) {
		this.expenseList = expenseList;
	}

}
