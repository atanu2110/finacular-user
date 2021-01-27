package com.finadv.entities;

/**
 * @author atanu
 *
 */
public class UserPointsUpdate {

	private long userId;

	private long points;

	private String action;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getPoints() {
		return points;
	}

	public void setPoints(long points) {
		this.points = points;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
