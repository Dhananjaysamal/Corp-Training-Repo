package com.example.demo.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicUpdate;


/*
 * 
 * One user has multiple accounts **/


@DynamicUpdate(value = true)
@Entity
@Table(name="UserRegistrationDetails" , 
      uniqueConstraints = { @UniqueConstraint (columnNames = {"user_Id"}) })
public class UserRegistrationEntity implements Serializable{

	private static final long serialVersionUID = 5615431718234184892L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_Id" , unique = true , nullable = false)
	private int userId;
	
	@Column(name = "mobile_number" )
	private String mobileNumber;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "user_address")
	private String userAddress;
	
	@Column(name = "account_balance")
	private Double accountBalance;
	
	@Column(name = "email_address", nullable = false )
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "Account_activate")
	private boolean accountEnabled;
	
	@OneToMany(mappedBy = "userRegistrationEntity" , fetch = FetchType.EAGER,cascade = CascadeType.PERSIST,targetEntity = AccountEntity.class)
	private Set<AccountEntity> accounts = new HashSet<>();
	
	public Set<AccountEntity> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<AccountEntity> accounts) {
		this.accounts = accounts;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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


	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAccountEnabled() {
		return accountEnabled;
	}

	public void setAccountEnabled(boolean accountEnabled) {
		this.accountEnabled = accountEnabled;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}
