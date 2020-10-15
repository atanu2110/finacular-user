package com.finadv.service.impl;

import com.finadv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finadv.entities.User;
import com.finadv.repository.UserRepository;

/**
 * @author atanu
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public void createUser(User user) {
		userRepository.save(user);

	}

	@Override
	public User updateUser(User user) {
		User userInDB = userRepository.findById(user.getId()).orElse(null);
		if (userInDB != null) {
			userRepository.save(user);
			return user;
		}

		return null;
	}

}
