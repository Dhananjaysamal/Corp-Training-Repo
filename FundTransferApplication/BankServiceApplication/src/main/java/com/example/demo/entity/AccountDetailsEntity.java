package com.example.demo.entity;

import java.io.Serializable;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "Account_Details")
@DynamicUpdate(value = true)
public class AccountDetailsEntity implements Serializable{

	private static final long serialVersionUID = 1107297600682705882L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@SequenceGenerator(name = "accountdetailsSeq" , sequenceName = "accountdetailsSeq" , allocationSize = 1)
	@Column(name = "account_Id" , unique = true , nullable = false)
	private int accountId;
	
	@Column(name = "account_number")
	private long accountNumber;
	
	@Column(name = "bank_name")
	private String bankName;
	
	@Column(name = "account_type")
	private String accountType;
	
	@Column(name = "mobile_number" )
	private String mobileNumber;

	@Column(name = "account_balance" , nullable = false)
	private Double accountBalance;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="user_Id" , nullable = false)
	private UserRegistrationEntity userRegistrationEntity;
	
	

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber( long accountNumber) {

		  String start = "";
	      Random value = new Random();
	      int n = 0;
		    for(int i =0; i < 12;i++)
		    {
		            n = value.nextInt(10);
		            start += Integer.toString(n);
		            
		    }
		    accountNumber = Long.parseLong(start);
		    this.accountNumber = accountNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public UserRegistrationEntity getUserRegistrationEntity() {
		return userRegistrationEntity;
	}

	public void setUserRegistrationEntity(UserRegistrationEntity userRegistrationEntity) {
		this.userRegistrationEntity = userRegistrationEntity;
	} 

}
