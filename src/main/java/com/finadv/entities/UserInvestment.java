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
@Table(name = "user_investment")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserInvestment {

	@Id
	@Column(name = "investment_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int investmentId;

	@Column(name = "user_id")
	private long userId;

	@Column(name = "investment_name")
	private String investmentName;

	@Column(name = "investment_on")
	private String investmentOn;

	@Column(name = "account_name")
	private String accountName;

	@Column(name = "investment_date")
	private Date investmentDate;

	@Column(name = "action")
	private String action;

	@Column(name = "unit_price")
	private float unitPrice;

	@Column(name = "investment_amount")
	private double investmentAmount;

	@Column(name = "maturity_date")
	private Date maturityDate;

	@Column(name = "created_on")
	private Date createdOn;

	public int getInvestmentId() {
		return investmentId;
	}

	public void setInvestmentId(int investmentId) {
		this.investmentId = investmentId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getInvestmentName() {
		return investmentName;
	}

	public void setInvestmentName(String investmentName) {
		this.investmentName = investmentName;
	}

	public String getInvestmentOn() {
		return investmentOn;
	}

	public void setInvestmentOn(String investmentOn) {
		this.investmentOn = investmentOn;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Date getInvestmentDate() {
		return investmentDate;
	}

	public void setInvestmentDate(Date investmentDate) {
		this.investmentDate = investmentDate;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getInvestmentAmount() {
		return investmentAmount;
	}

	public void setInvestmentAmount(double investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
