package com.finadv.controller;

import com.finadv.entities.Income;
import com.finadv.model.dto.IncomeDTO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/income")
public interface IncomeApi {

    @ApiOperation(value = "Deletes an income item", nickname = "deleteIncome", notes = "", tags = {"income-tracker",})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Income not found")})
    @RequestMapping(value = "/{incomeId}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<String> deleteIncome(@ApiParam(value = "Income id to delete", required = true) @PathVariable("incomeId")
                                                Long incomeId);


    @ApiOperation(value = "Find income by ID", nickname = "getIncomeById", notes = "Returns a single Income Object",
            response = IncomeDTO.class, tags = {"income-tracker",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = IncomeDTO.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Income not found")})
    @RequestMapping(value = "/{incomeId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Income> getIncomeById(@ApiParam(value = "ID of Income to return", required = true) @PathVariable("incomeId")
                                                 Long incomeId);

    @ApiOperation(value = "Add an income item", nickname = "addIncome", notes = "", tags = {"income-tracker",})
    @ApiResponses(value = {
            @ApiResponse(code = 405, message = "Invalid input")})
    @RequestMapping(value = "/add",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Income> addIncome(@ApiParam(value = "Income object that needs to be added to the user incomes", required = true)
                                     @Valid @RequestBody IncomeDTO body);


    @ApiOperation(value = "Update an existing income", nickname = "updateIncome", notes = "", tags = {"income-tracker"})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Income not found"),
            @ApiResponse(code = 405, message = "Validation exception")})
    @RequestMapping(value = "/update",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Income> updateIncome(@ApiParam(value = "Income object that needs to be added/updated to the store", required = true)
                                        @Valid @RequestBody IncomeDTO body);

    @ApiOperation(value = "Finds Income by the user", nickname = "findIncomeByuser", notes = "", response = IncomeDTO.class,
            responseContainer = "List", tags = {"income-tracker"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = IncomeDTO.class, responseContainer = "List")})
    @RequestMapping(value = "/list",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Income>> listIncomeByUser();

}
