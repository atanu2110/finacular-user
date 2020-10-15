package com.finadv.repository;

import com.finadv.entities.Expense;
import com.finadv.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {
    List<Expense> findByUser(User user);
}
