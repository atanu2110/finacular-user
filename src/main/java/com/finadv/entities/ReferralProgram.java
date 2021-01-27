package com.finadv.entities;

import java.util.Date;

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
@Table(name = "referral_program")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ReferralProgram {

	@Id
	@Column(name = "code_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int codeId;

	@Column(name = "user_id")
	private long userId;

	@Column(name = "referral_code")
	private String referralCode;

	@Column(name = "referrer_code")
	private String referrerCode;

	@Column(name = "created_date")
	private Date createdDate;

	public int getCodeId() {
		return codeId;
	}

	public void setCodeId(int codeId) {
		this.codeId = codeId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}

	public String getReferrerCode() {
		return referrerCode;
	}

	public void setReferrerCode(String referrerCode) {
		this.referrerCode = referrerCode;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
