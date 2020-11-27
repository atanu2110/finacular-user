package com.finadv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finadv.dto.UserExpenseList;
import com.finadv.entities.ExpenseCategory;
import com.finadv.entities.UserExpense;
import com.finadv.service.ExpenseService;

/**
 * @author atanu
 *
 */
@RestController
@RequestMapping(value = "/api/v1/expense")
@CrossOrigin(origins = "*")
public class ExpenseController {

	private ExpenseService expenseService;

	@Autowired
	public void setExpenseService(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}

	/**
	 * @return list of expense category
	 */
	@GetMapping("/category")
	public ResponseEntity<List<ExpenseCategory>> getAllExpenseCategory() {
		return new ResponseEntity<>(expenseService.getAllExpenseCatrgory(), HttpStatus.OK);
	}

	/**
	 * @param userId
	 * @return list of user expenses
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<UserExpenseList> getUserExpenses(@PathVariable long userId) {
		return new ResponseEntity<>(expenseService.getUserExpenses(userId), HttpStatus.OK);
	}

	/**
	 * @param userExpense
	 * @return create user expense
	 */
	@PostMapping("/{userId}")
	public ResponseEntity<String> createUserExpense(@RequestBody UserExpenseList userExpenseList) {
		expenseService.createUserExpense(userExpenseList);
		return new ResponseEntity<>("User Expense successfully saved !!", HttpStatus.OK);
	}

	/**
	 * @param userExpense
	 * @return updated user expense
	 */
	@PutMapping("/{expenseId}")
	public ResponseEntity<?> updateUserExpense(@RequestBody UserExpense userExpense) {
		UserExpense userExpenseUpdated = expenseService.updateUserExpense(userExpense);
		if (userExpenseUpdated != null) {
			return new ResponseEntity<>(userExpenseUpdated, HttpStatus.OK);
		}

		return new ResponseEntity<>("Expense does not exists !!", HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param expenseId
	 * @return delete expense
	 */
	@DeleteMapping("/{expenseId}")
	public ResponseEntity<?> deleteUserExpense(@PathVariable long expenseId) {
		expenseService.deleteUserExpense(expenseId);
		return new ResponseEntity<>("Expense deleted successfully !!", HttpStatus.OK);

	}

}
