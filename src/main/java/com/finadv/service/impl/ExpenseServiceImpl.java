package com.finadv.service.impl;

import com.finadv.entities.Expense;
import com.finadv.entities.User;
import com.finadv.model.dto.ExpenseDTO;
import com.finadv.repository.ExpenseRepo;
import com.finadv.service.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static java.sql.Timestamp.from;
import static java.time.Instant.now;

@Service
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepo expenseRepo;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepo expenseRepo) {
        this.expenseRepo = expenseRepo;
    }

    @Override
    public Expense addExpense(ExpenseDTO expenseDTO) {
        Expense expense = new Expense(expenseDTO);
        expense.setCreatedAt(from(now()));
        //set user from security
        return expenseRepo.save(expense);
    }

    @Override
    public Expense update(ExpenseDTO expenseDTO) {
        Optional<Expense> existingExpense =  expenseRepo.findById(expenseDTO.getId());
        return existingExpense.map(expense -> {
            expense.setAmount(expenseDTO.getAmount());
            expense.setCategory(expenseDTO.getCategory().name());
            expense.setName(expenseDTO.getName());
            expense.setPeriod(expenseDTO.getPeriod().name());
            expense.setModifiedAt(from(now()));
            return expenseRepo.save(expense);
        }).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Expense> listExpenseForCurrentUser() {
//        User user =  authService.getUser();
        User user = new User();
        user.setId(1L);
        return expenseRepo.findByUser(user);
    }

    @Override
    public void deleteExpense(Long incomeId) {
        expenseRepo.deleteById(incomeId);
    }

    @Override
    public Expense getExpense(Long incomeId) {
        Optional<Expense> optionalExpense = expenseRepo.findById(incomeId);
        if (optionalExpense.isPresent()) {
            return optionalExpense.get();
        } else throw new NoSuchElementException();
    }
}
