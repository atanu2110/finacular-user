package com.finadv.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finadv.entities.ReferralProgram;
import com.finadv.entities.User;
import com.finadv.entities.UserPoints;
import com.finadv.entities.UserPointsUpdate;
import com.finadv.service.UserService;

/**
 * @author atanu
 *
 */
@RestController
@RequestMapping(value = "/api/v1")
@CrossOrigin(origins = "*")
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
	public ResponseEntity<?> showUser(@PathVariable int id) {

		User user = userService.getUserById(id);
		if (user != null)
			return new ResponseEntity<>(user, HttpStatus.OK);
		return new ResponseEntity<>("User id is not present", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/users")
	public ResponseEntity<?> getUserByEmailId(@PathParam(value = "emailId") String emailId) {

		User user = userService.getUserByEmailId(emailId);
		if (user != null)
			return new ResponseEntity<>(user, HttpStatus.OK);
		return new ResponseEntity<>("User with email is not present", HttpStatus.NOT_FOUND);
	}

	/**
	 * @param user
	 * @return
	 */
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User createdUser = userService.createUser(user);
		return new ResponseEntity<>(createdUser, HttpStatus.OK);
	}

	/**
	 * @param user
	 * @param id
	 * @return
	 */
	@PutMapping("/users/{id}")
	public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable int id) {
		user.setId(id);
		User updatedUser = userService.updateUser(user);

		if (updatedUser != null)
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		return new ResponseEntity<>("User id is not present", HttpStatus.NOT_FOUND);
	}

	/**
	 * @param referralProgram
	 * @return
	 */
	@PostMapping("/users/referral")
	public String createUserReferralCode(@RequestBody ReferralProgram referralProgram) {
		userService.createUserReferralCode(referralProgram);
		return "Referral Code successfully generated !!";
	}

	/**
	 * @param userId
	 * @return referral program
	 */
	@GetMapping("/users/referral")
	public ResponseEntity<?> getCodeByUserId(@PathParam(value = "userId") long userId) {

		ReferralProgram referralProgram = userService.getCodeByUserId(userId);
		if (referralProgram != null)
			return new ResponseEntity<>(referralProgram, HttpStatus.OK);
		return new ResponseEntity<>("Referral Program user not present", HttpStatus.NOT_FOUND);
	}
	
	
	/**
	 * @param userPointsUpdate
	 * @return
	 */
	@PutMapping("/users/points")
	public ResponseEntity<?> updateUserPoints(@RequestBody UserPointsUpdate userPointsUpdate) {

		UserPoints userPoints = userService.updateUserPoints(userPointsUpdate);
		if (userPoints != null)
			return new ResponseEntity<>(userPoints, HttpStatus.OK);
		return new ResponseEntity<>("User points for userId not present", HttpStatus.NOT_FOUND);
	}
	
	
	
	/**
	 * @param userId
	 * @return
	 */
	@GetMapping("/users/points/{userId}")
	public ResponseEntity<?> getUserPoints(@PathVariable long userId) {

		UserPoints userPoints = userService.getUserPoints(userId);
		if (userPoints != null)
			return new ResponseEntity<>(userPoints, HttpStatus.OK);
		return new ResponseEntity<>("User points for userId not present", HttpStatus.NOT_FOUND);
	}

}
