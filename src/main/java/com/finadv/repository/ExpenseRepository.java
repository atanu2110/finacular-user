package com.finadv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finadv.entities.UserExpense;

/**
 * @author atanu
 *
 */
@Repository
public interface ExpenseRepository extends JpaRepository<UserExpense, Integer> {

	@Query(value="SELECT * FROM user_expense u WHERE u.user_id = :userid", nativeQuery = true)
	List<UserExpense> findUserExpensesByUserId(@Param("userid") Long id);
}
