package com.finadv.service;

import org.springframework.stereotype.Service;

import com.finadv.entities.User;

/**
 * @author atanu
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Override
	public User getUserById(int id) {
		User user = new User();
		user.setFirstName("Test");
		user.setLastName("User");
		user.setId(1);
		return user;
	}

}
