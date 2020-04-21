package com.example.demo.bean;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {

	
	@NotEmpty(message = "first name must not be empty")
	private String firstName;
	
	@NotEmpty(message = "last name must not be empty")
	private String lastName;
	
	
	private int userId;
	
	private List<Long> accountNumbers;
	
	@NotEmpty(message = "userAddress must not be empty")
	private String userAddress;
	
	@Min(10000)
	@NotNull(message = "account Balance should be filled")
	private Double accountBalance;
	
	@NotEmpty(message = "email must not be empty")
    @Email(message = "email should be a valid email")
	@Pattern(regexp=".+@.+\\.[a-z]+")
	private String email;
	
	@NotEmpty(message = "password must not be empty")
	private String password;
	
	
	private boolean accountEnabled;
	
	private List<String> accountType;
	
	@NotEmpty(message = "MobileNumber should be filled")
	@Size(min = 10 , max = 10 , message = " Mobile number should be 10 digit without prefixing '0' or country code")
	@Pattern(regexp = "\\d+")
	private String mobileNumber;
	
	@Size(min = 16 , max = 16 , message = " CradNumber should be 16 digit")
	private String cardNumber;
	
	
	private int cvv;
	
	private Date expDate;
	
	private String isRegSuccess ="User Account Registration is failed";
	
	public String getIsRegSuccess() {
		return isRegSuccess;
	}
	public void setIsRegSuccess(String isRegSuccess) {
		this.isRegSuccess = isRegSuccess;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	public List<String> getAccountType() {
		return accountType;
	}
	public void setAccountType(List<String> accountType) {
		this.accountType = accountType;
	}
	public Double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}
//	public String getAccountType() {
//		return accountType;
//	}
//	public void setAccountType(String accountType) {
//		this.accountType = accountType;
//	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
//	public List<String> getAccountType() {
//		return accountType;
//	}
//	public void setAccountType(List<String> accountType) {
//		this.accountType = accountType;
//	}
	public List<Long> getAccountNumbers() {
		return accountNumbers;
	}
	public void setAccountNumbers(List<Long> accountNumbers) {
		this.accountNumbers = accountNumbers;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
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
	


}
