package com.finadv.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finadv.entities.UserPoints;

@Repository
public interface UserPointsRepository extends CrudRepository<UserPoints, Integer> {

	@Query(value = "SELECT * FROM user_points r WHERE r.user_id = :userId", nativeQuery = true)
	UserPoints getPointsByUserId(@Param("userId") long userId);

}
