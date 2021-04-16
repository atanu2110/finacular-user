package com.finadv.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author atanu
 *
 */
@Entity
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "second_name")
	private String secondName;

	@Column(name = "age")
	private int age;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@Column(name = "is_verified")
	private boolean isVerified;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "is_satbleincome")
	private boolean isStableIncome;

	@Column(name = "is_stayinvested")
	private boolean isStayInvested;

	@Column(name = "tax_bracket")
	private int taxBracket;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "dob")
	private String dob;

	@Column(name = "gender")
	private String gender;

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isStableIncome() {
		return isStableIncome;
	}

	public void setStableIncome(boolean isStableIncome) {
		this.isStableIncome = isStableIncome;
	}

	public boolean isStayInvested() {
		return isStayInvested;
	}

	public void setStayInvested(boolean isStayInvested) {
		this.isStayInvested = isStayInvested;
	}

	public int getTaxBracket() {
		return taxBracket;
	}

	public void setTaxBracket(int taxBracket) {
		this.taxBracket = taxBracket;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
