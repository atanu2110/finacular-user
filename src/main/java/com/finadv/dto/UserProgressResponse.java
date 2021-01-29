package com.finadv.dto;

import java.util.List;

/**
 * @author atanu
 *
 */
public class UserProgressResponse {

	private String badge;

	private List<String> nextGoals;

	private List<UserGameStatus> progress;

	public String getBadge() {
		return badge;
	}

	public void setBadge(String badge) {
		this.badge = badge;
	}

	public List<String> getNextGoals() {
		return nextGoals;
	}

	public void setNextGoals(List<String> nextGoals) {
		this.nextGoals = nextGoals;
	}

	public List<UserGameStatus> getProgress() {
		return progress;
	}

	public void setProgress(List<UserGameStatus> progress) {
		this.progress = progress;
	}

}
