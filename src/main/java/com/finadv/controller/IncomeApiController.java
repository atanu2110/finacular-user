package com.finadv.controller;


import com.finadv.entities.Income;
import com.finadv.model.dto.IncomeDTO;
import com.finadv.service.IncomeService;
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
public class IncomeApiController implements IncomeApi {

    private final IncomeService incomeService;

    @Autowired
    public IncomeApiController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    public ResponseEntity<Income> addIncome(@ApiParam(value = "Income object that needs to be added to the user incomes",
            required = true) @Valid @RequestBody IncomeDTO incomeDTO) {
        Income addedIncome = incomeService.addIncome(incomeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedIncome);
    }

    public ResponseEntity<Income> updateIncome(@ApiParam(value = "Income object that needs to be added/updated to the store",
            required = true) @Valid @RequestBody IncomeDTO incomeDTO) {
        Income updatedIncome = incomeService.update(incomeDTO);
        return ResponseEntity.ok(updatedIncome);
    }


    public ResponseEntity<List<Income>> listIncomeByUser() {
        List<Income> incomeList =  incomeService.listIncomeForCurrentUser();
        return ResponseEntity.ok(incomeList);
    }

    public ResponseEntity<String> deleteIncome(@ApiParam(value = "Income id to delete", required = true)
                                             @PathVariable("incomeId") Long incomeId) {
        incomeService.deleteIncome(incomeId);
        return ResponseEntity.ok("Delete Income");

    }

    public ResponseEntity<Income> getIncomeById(@ApiParam(value = "ID of Income to return", required = true)
                                                @PathVariable("incomeId") Long incomeId) {
        Income income = incomeService.getIncome(incomeId);
        return ResponseEntity.ok(income);
    }
}
