package com.finadv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.finadv.entities.User;

/**
 * @author atanu
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

}
