package com.finadv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finadv.entities.User;
import com.finadv.service.UserService;

/**
 * @author atanu
 *
 */
@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * @return
	 */
	@GetMapping(value = "/test")
	public String test() {
		return "user service is up";
	}

	/**
	 * View a specific user by its id.
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/users/{id}")
	public User showUser(@PathVariable int id) {
		return userService.getUserById(id);

	}

	/**
	 * @param user
	 * @return
	 */
	@PostMapping("/users")
	public String createUser(@RequestBody User user) {
		userService.createUser(user);
		return "User successfully created";
	}

	/**
	 * @param user
	 * @param id
	 * @return
	 */
	@PutMapping("/users/{id}")
	public User updateUser(@RequestBody User user, @PathVariable int id) {
		user.setId(id);
		User updatedUser = userService.updateUser(user);

		return updatedUser;
	}
}
