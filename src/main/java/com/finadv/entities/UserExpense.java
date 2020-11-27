package com.finadv.entities;

import java.util.Date;

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
@Table(name = "user_expense")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserExpense {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int expenseId;

	@OneToOne
	@JoinColumn(name = "category", referencedColumnName = "category_id")
	private ExpenseCategory category;

	@Column(name = "transaction_date")
	private Date transactionDate;

	@Column(name = "payment_by")
	private String paymentBy;

	@Column(name = "payment_type")
	private String paymentType;

	@Column(name = "payment_src")
	private String paymentSource;

	@Column(name = "payment_to")
	private String paymentTo;

	@Column(name = "amount")
	private long amount;

	//@Column(name = "group")
	//private String group;

	@Column(name = "user_id")
	private long userId;

	public int getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}

	public ExpenseCategory getCategory() {
		return category;
	}

	public void setCategory(ExpenseCategory category) {
		this.category = category;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getPaymentBy() {
		return paymentBy;
	}

	public void setPaymentBy(String paymentBy) {
		this.paymentBy = paymentBy;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentSource() {
		return paymentSource;
	}

	public void setPaymentSource(String paymentSource) {
		this.paymentSource = paymentSource;
	}

	public String getPaymentTo() {
		return paymentTo;
	}

	public void setPaymentTo(String paymentTo) {
		this.paymentTo = paymentTo;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	/*
	 * public String getGroup() { return group; }
	 * 
	 * public void setGroup(String group) { this.group = group; }
	 */
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
