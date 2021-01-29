package com.finadv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.finadv.entities.UserGameProgress;

/**
 * @author atanu
 *
 */
public interface UserGameProgressRepository extends JpaRepository<UserGameProgress, Integer> {

	@Query(value = "SELECT * FROM user_progress u WHERE u.user_id = :userid", nativeQuery = true)
	List<UserGameProgress> findAllByUserId(@Param("userid") Long id);

	@Query(value = "SELECT * FROM user_progress u WHERE u.user_id = :userid AND u.goal = :goalid", nativeQuery = true)
	UserGameProgress findByUserIdAndGoalId(@Param("userid") Long id, @Param("goalid") Long goalid);
}
