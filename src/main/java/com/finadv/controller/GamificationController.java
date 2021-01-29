package com.finadv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finadv.entities.User;
import com.finadv.entities.UserGameProgress;
import com.finadv.service.GameService;

/**
 * @author atanu
 *
 */
@RestController
@RequestMapping(value = "/api/v1")
@CrossOrigin(origins = "*")
public class GamificationController {

	private GameService gameService;

	@Autowired
	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	/**
	 * @return all goals
	 */
	@GetMapping("/goals")
	public ResponseEntity<?> getAllGoals() {

		return new ResponseEntity<>(gameService.getAllGoals(), HttpStatus.OK);
	}

	@GetMapping("/users/goals/progress/{id}")
	public ResponseEntity<?> userProgress(@PathVariable int id) {

		return new ResponseEntity<>(gameService.getUserProgress(id), HttpStatus.OK);
	}

	/**
	 * @param user
	 * @return
	 */
	@PostMapping("/users/goals/progress")
	public ResponseEntity<String> createUserProgress(@RequestBody UserGameProgress userGameProgress) {
		gameService.createUserProgress(userGameProgress);
		return new ResponseEntity<>("User progress created!!", HttpStatus.OK);
	}
}
