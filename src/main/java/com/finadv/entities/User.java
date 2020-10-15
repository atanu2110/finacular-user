package com.finadv.entities;

import lombok.Data;

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
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

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
}
