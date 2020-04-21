package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;
import java.util.StringJoiner;

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
@Table(name = "AccountDetails")
@DynamicUpdate(value = true)
public class AccountEntity implements Serializable{
	


	private static final long serialVersionUID = 1107297600682705882L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_Id" , unique = true , nullable = false)
	private int accountId;
	
	@Column(name = "account_number" , unique = true)
	private long accountNumber;
	
	@Column(name = "bank_name")
	private String bankName;
	
	@Column(name = "account_type")
	private String accountType;
	
	@Column(name = "mobile_number" )
	private String mobileNumber;

	@Column(name = "account_balance" , nullable = false)
	private Double accountBalance;
	
	@Column( name ="card_Number " , nullable = false )
	private String cardNumber;
	
	@Column(name = "cvv")
	private int cvv;
	
	@Column(name = "expiryDate")
	private Date expiryDate;
	
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="user_Id" , nullable = false )
	private UserRegistrationEntity userRegistrationEntity;
	
	

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) { 
//		if(cardNumber.equals("")) {
		Random value = new Random();
		StringJoiner sj = new StringJoiner("");
		int n = 0;
		for(int i =0; i < 4;i++)
		{
			cardNumber = "";

			for(int j=0;j<4;) {
				j++;
				n = value.nextInt(10);
				cardNumber += Integer.toString(n);
			}
			sj.add(cardNumber);
		}
		this.cardNumber=sj.toString();
//		}else {
//			this.cardNumber=cardNumber;
//		}
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
//		if(cvv==0) {
		Random value = new Random();
		String newCvv = Integer.toString(cvv);
		StringJoiner sj = new StringJoiner("");
		int n = 0;
		newCvv = "";

			for(int j=0;j<3;) {
				j++;
				n = value.nextInt(10);
				newCvv += Integer.toString(n);
			}
			sj.add(newCvv);
			this.cvv=Integer.parseInt(sj.toString());
//		}else {
//			this.cvv=cvv;
//		}
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

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
