package com.finadv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finadv.entities.ExpenseCategory;

/**
 * @author atanu
 *
 */
@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Integer> {

}
