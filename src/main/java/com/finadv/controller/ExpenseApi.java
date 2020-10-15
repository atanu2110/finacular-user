package com.finadv.controller;

import com.finadv.entities.Expense;
import com.finadv.model.dto.ExpenseDTO;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Api("Expense APIs")
@RequestMapping(value = "/expenses")
@Service
public interface ExpenseApi {


    @ApiOperation(value = "Add an expense item", nickname = "addExpense", notes = "", tags = {"expense-tracker",})
    @ApiResponses(value = {
            @ApiResponse(code = 405, message = "Invalid input")})
    @RequestMapping(value = "/add",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Expense> addExpense(@ApiParam(value = "Expense object that needs to be added to the user expenses", required = true)
                                    @Valid @RequestBody ExpenseDTO expenseDTO);


    @ApiOperation(value = "Update an existing expense", nickname = "updateExpense", notes = "", tags = {"expense-tracker",})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Expense not found"),
            @ApiResponse(code = 405, message = "Validation exception")})
    @RequestMapping(value = "/update",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Expense> updateExpense(@ApiParam(value = "Expense object that needs to be added/updated to the store", required = true)
                                       @Valid @RequestBody ExpenseDTO expenseDTO);


    @ApiOperation(value = "Finds Expenses by the user", nickname = "findExpenseByuser", response = Expense.class,
            responseContainer = "List", tags = {"expense-tracker",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Expense.class, responseContainer = "List")})
    @RequestMapping(value = "/list",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Expense>> listExpenseByUser();

    @ApiOperation(value = "Deletes an expense item", nickname = "deleteExpense", notes = "",
            tags = {"expense-tracker",})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Expense not found")})
    @RequestMapping(value = "/{expenseId}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<String> deleteExpense(@ApiParam(value = "Expense id to delete", required = true)
                                       @PathVariable("expenseId") Long expenseId);

    @ApiOperation(value = "Find expense by ID", nickname = "getExpenseById", notes = "Returns a single Expense Object",
            response = Expense.class, tags = {"expense-tracker",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Expense.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Expense not found")})
    @RequestMapping(value = "/{expenseId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Expense> getExpenseById(@ApiParam(value = "ID of Expense to return", required = true)
                                           @PathVariable("expenseId") Long expenseId);

}
