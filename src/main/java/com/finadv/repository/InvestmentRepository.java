package com.finadv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.finadv.entities.UserInvestment;

/**
 * @author atanu
 *
 */
public interface InvestmentRepository extends JpaRepository<UserInvestment, Integer> {
	
	@Query(value="SELECT * FROM user_investment u WHERE u.user_id = :userid", nativeQuery = true)
	List<UserInvestment> findUserInvestmentByUserId(@Param("userid") Long id);

}
