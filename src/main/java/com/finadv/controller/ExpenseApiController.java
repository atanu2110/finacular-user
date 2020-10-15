package com.finadv.controller;

import com.finadv.entities.Expense;
import com.finadv.model.dto.ExpenseDTO;
import com.finadv.service.ExpenseService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@Slf4j
public class ExpenseApiController implements ExpenseApi {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseApiController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }


    public ResponseEntity<Expense> addExpense(@ApiParam(value = "Expense object that needs to be added to the user expenses", required = true)
                                           @Valid @RequestBody ExpenseDTO expenseDTO) {
        Expense addedExpense = expenseService.addExpense(expenseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedExpense);

    }

    public ResponseEntity<Expense> updateExpense(@ApiParam(value = "Expense object that needs to be added/updated to the store",
            required = true) @Valid @RequestBody ExpenseDTO expenseDTO) {
        Expense updatedExpense = expenseService.update(expenseDTO);
        return ResponseEntity.ok(updatedExpense);

    }

    public ResponseEntity<List<Expense>> listExpenseByUser() {
        List<Expense> expenseList =  expenseService.listExpenseForCurrentUser();
        return ResponseEntity.ok(expenseList);

    }

    public ResponseEntity<String> deleteExpense(@ApiParam(value = "Expense id to delete", required = true)
                                              @PathVariable("expenseId") Long expenseId) {
        expenseService.deleteExpense(expenseId);
        return ResponseEntity.ok("Deleted Expense");
    }

    public ResponseEntity<Expense> getExpenseById(@ApiParam(value = "ID of Expense to return", required = true)
                                                  @PathVariable("expenseId") Long expenseId) {
        Expense expense = expenseService.getExpense(expenseId);
        return ResponseEntity.ok(expense);
    }

}
