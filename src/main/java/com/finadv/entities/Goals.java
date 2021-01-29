package com.finadv.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author atanu
 *
 */
@Entity
@Table(name = "goals")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Goals {

	@Id
	@Column(name = "goal_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long goalId;

	@Column(name = "goal_description")
	private String goalDescription;

	@Column(name = "points")
	private int points;

	@OneToOne
	@JoinColumn(name = "badge", referencedColumnName = "badge_id")
	private Badges badge;

	public long getGoalId() {
		return goalId;
	}

	public void setGoalId(long goalId) {
		this.goalId = goalId;
	}

	public String getGoalDescription() {
		return goalDescription;
	}

	public void setGoalDescription(String goalDescription) {
		this.goalDescription = goalDescription;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Badges getBadge() {
		return badge;
	}

	public void setBadge(Badges badge) {
		this.badge = badge;
	}

}
