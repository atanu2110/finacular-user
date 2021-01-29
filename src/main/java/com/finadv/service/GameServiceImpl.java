package com.finadv.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finadv.dto.UserGameStatus;
import com.finadv.dto.UserProgressResponse;
import com.finadv.entities.Goals;
import com.finadv.entities.UserGameProgress;
import com.finadv.entities.UserPointsUpdate;
import com.finadv.repository.GoalRepository;
import com.finadv.repository.UserGameProgressRepository;

/**
 * @author atanu
 *
 */
@Service
public class GameServiceImpl implements GameService {

	private GoalRepository goalRepository;
	private UserGameProgressRepository userGameProgressRepository;

	private UserService userService;

	@Autowired
	public void setGoalRepository(GoalRepository goalRepository) {
		this.goalRepository = goalRepository;
	}

	@Autowired
	public void setUserGameProgressRepository(UserGameProgressRepository userGameProgressRepository) {
		this.userGameProgressRepository = userGameProgressRepository;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public List<Goals> getAllGoals() {
		return goalRepository.findAll();
	}

	@Override
	public UserProgressResponse getUserProgress(long userId) {
		List<Goals> allGoals = goalRepository.findAll();
		UserProgressResponse userProgressResponse = new UserProgressResponse();
		List<UserGameProgress> achievements = userGameProgressRepository.findAllByUserId(userId);
		List<UserGameStatus> progress = new ArrayList<UserGameStatus>();
		List<String> nextGoals = new ArrayList<String>();
		List<Goals> completedList = new ArrayList<Goals>();

		for (Goals goals : allGoals) {
			UserGameStatus gameStatus = new UserGameStatus();
			boolean isPresent = achievements.stream().filter(a -> a.getGoal().getGoalId() == goals.getGoalId())
					.findFirst().isPresent();
			gameStatus.setGoal(goals.getGoalDescription());
			gameStatus.setStatus(isPresent ? "COMPLETED" : "PENDING");

			progress.add(gameStatus);

			if (!isPresent) {
				nextGoals.add(goals.getGoalDescription());
			} else {
				completedList.add(goals);
			}

		}
		userProgressResponse.setNextGoals(nextGoals);
		userProgressResponse.setProgress(progress);

		// get user badge
		userProgressResponse.setBadge(getUserBadge(completedList, allGoals));

		return userProgressResponse;
	}

	@Override
	public void createUserProgress(UserGameProgress userGameProgress) {
		UserGameProgress userGoalProgress = userGameProgressRepository
				.findByUserIdAndGoalId(userGameProgress.getUserId(), userGameProgress.getGoal().getGoalId());

		if (userGoalProgress == null) {
			userGameProgressRepository.save(userGameProgress);

			// Add points for the user
			UserPointsUpdate userPoints = new UserPointsUpdate();
			userPoints.setUserId(userGameProgress.getUserId());
			userPoints.setAction("ADD");
			Goals goal = goalRepository.findById(userGameProgress.getGoal().getGoalId()).orElse(null);
			userPoints.setPoints(goal.getPoints());
			userService.updateUserPoints(userPoints);
		}

	}

	private String getUserBadge(List<Goals> completedList, List<Goals> allGoals) {
		String badge = "NEWBIE";
		Map<Integer, List<Goals>> goalByBadges = allGoals.stream()
				.collect(Collectors.groupingBy(a -> a.getBadge().getAchievementLevel()));
		for (Map.Entry<Integer, List<Goals>> entry : goalByBadges.entrySet()) {
			List<Goals> sameAchievementCompletedGoalList = completedList.stream()
					.filter(a -> a.getBadge().getAchievementLevel() == entry.getKey()).collect(Collectors.toList());
			if (sameAchievementCompletedGoalList.size() > 0)
				badge = sameAchievementCompletedGoalList.get(0).getBadge().getBadgeName();

			if (!(sameAchievementCompletedGoalList.size() == entry.getValue().size()))
				break;
		}

		return badge;

	}
}
