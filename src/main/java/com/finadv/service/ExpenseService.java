package com.finadv.service;


import com.finadv.entities.Expense;
import com.finadv.model.dto.ExpenseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpenseService {

    Expense addExpense(ExpenseDTO incomeDTO);

    Expense update(ExpenseDTO incomeDTO);

    List<Expense> listExpenseForCurrentUser();

    void deleteExpense(Long incomeId);

    Expense getExpense(Long incomeId);
}
