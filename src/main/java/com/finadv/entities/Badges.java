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
@Table(name = "badges")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Badges {

	@Id
	@Column(name = "badge_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long badgeId;

	@Column(name = "badge_name")
	private String badgeName;

	@Column(name = "achievement_level")
	private int achievementLevel;

	public long getBadgeId() {
		return badgeId;
	}

	public void setBadgeId(long badgeId) {
		this.badgeId = badgeId;
	}

	public String getBadgeName() {
		return badgeName;
	}

	public void setBadgeName(String badgeName) {
		this.badgeName = badgeName;
	}

	public int getAchievementLevel() {
		return achievementLevel;
	}

	public void setAchievementLevel(int achievementLevel) {
		this.achievementLevel = achievementLevel;
	}

}
