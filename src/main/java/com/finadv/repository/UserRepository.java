package com.finadv.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finadv.entities.User;

/**
 * @author atanu
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

	@Query(value="SELECT * FROM users u WHERE u.email = :emailId", nativeQuery = true)
	User getUserByEmail(@Param("emailId") String emailId);
	
}
