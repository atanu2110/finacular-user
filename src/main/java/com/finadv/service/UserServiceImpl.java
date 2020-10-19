package com.finadv.service;

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
	public User getUserById(int id) {
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

	@Override
	public User getUserByEmailId(String emailId) {
		User userInDB = userRepository.getUserByEmail(emailId);
		if (userInDB != null)
			return userInDB;

		return null;
	}

}
