package com.finadv.service;

import com.finadv.entities.User;

public interface UserService {

	User getUserById(int id);
	
	void createUser(User user);
}
