package com.finadv.entities;

import java.time.LocalDateTime;

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
@Table(name = "user_progress")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserGameProgress {

	@Id
	@Column(name = "progress_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long progressId;

	@Column(name = "user_id")
	private long userId;

	@OneToOne
	@JoinColumn(name = "goal", referencedColumnName = "goal_id")
	private Goals goal;

	@Column(name = "completed_on")
	private LocalDateTime completedOn;

	public long getProgressId() {
		return progressId;
	}

	public void setProgressId(long progressId) {
		this.progressId = progressId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Goals getGoal() {
		return goal;
	}

	public void setGoal(Goals goal) {
		this.goal = goal;
	}

	public LocalDateTime getCompletedOn() {
		return completedOn;
	}

	public void setCompletedOn(LocalDateTime completedOn) {
		this.completedOn = completedOn;
	}

}
