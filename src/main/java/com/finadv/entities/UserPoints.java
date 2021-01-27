package com.finadv.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author atanu
 *
 */
@Entity
@Table(name = "user_points")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserPoints {

	@Id
	@Column(name = "points_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long pointsId;

	@Column(name = "user_id")
	private long userId;

	@Column(name = "points")
	private long points;

	public long getPointsId() {
		return pointsId;
	}

	public void setPointsId(long pointsId) {
		this.pointsId = pointsId;
	}

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

}
