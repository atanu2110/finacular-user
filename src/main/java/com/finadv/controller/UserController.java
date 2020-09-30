package com.finadv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public User showProduct(@PathVariable int id) {
		return userService.getUserById(id);

	}
}
