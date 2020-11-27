package com.finadv.controller;

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

import com.finadv.dto.UserInvestmentList;
import com.finadv.entities.UserInvestment;
import com.finadv.service.ExpenseService;

/**
 * @author atanu
 *
 */
@RestController
@RequestMapping(value = "/api/v1/invest")
@CrossOrigin(origins = "*")
public class InvestmentController {

	private ExpenseService expenseService;

	@Autowired
	public void setExpenseService(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}

	/**
	 * @param userId
	 * @return list of user expenses
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<UserInvestmentList> getUserInvestment(@PathVariable long userId) {
		return new ResponseEntity<>(expenseService.getUserInvestment(userId), HttpStatus.OK);
	}

	/**
	 * @param userInvestment
	 * @return create user investment
	 */
	@PostMapping("/{userId}")
	public ResponseEntity<String> createUserInvestment(@RequestBody UserInvestmentList userInvestList) {
		expenseService.createUserInvestment(userInvestList);
		return new ResponseEntity<>("User Investment successfully saved !!", HttpStatus.OK);
	}

	/**
	 * @param userInvestment
	 * @return updated user investment
	 */
	@PutMapping("/{investmentId}")
	public ResponseEntity<?> updateUserInvestment(@RequestBody UserInvestment userInvestment) {

		UserInvestment userInvestmentUpdated = expenseService.updateUserInvestment(userInvestment);
		if (userInvestmentUpdated != null) {
			return new ResponseEntity<>(userInvestmentUpdated, HttpStatus.OK);
		}

		return new ResponseEntity<>("Investment does not exists !!", HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param investmentId
	 * @return delete investment
	 */
	@DeleteMapping("/{investmentId}")
	public ResponseEntity<?> deleteUserInvestment(@PathVariable long investmentId) {
		expenseService.deleteUserInvestment(investmentId);
		return new ResponseEntity<>("Investment deleted successfully !!", HttpStatus.OK);

	}

}
