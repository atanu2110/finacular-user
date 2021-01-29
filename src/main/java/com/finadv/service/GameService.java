package com.finadv.service;

import java.util.List;

import com.finadv.dto.UserProgressResponse;
import com.finadv.entities.Goals;
import com.finadv.entities.UserGameProgress;

public interface GameService {

	List<Goals> getAllGoals();

	UserProgressResponse getUserProgress(long userId);

	void createUserProgress( UserGameProgress userGameProgress);
}
