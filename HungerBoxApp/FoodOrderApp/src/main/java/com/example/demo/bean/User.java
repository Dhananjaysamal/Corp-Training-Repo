package com.example.demo.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {

	private int userId;
	
	@NotEmpty(message = "first name must not be empty")
	private String name;
	
	@NotEmpty(message = "MobileNumber should be filled")
	@Size(min = 10 , max = 10 , message = " Mobile number should be 10 digit without prefixing '0' or country code")
	@Pattern(regexp = "\\d+")
	private String mobileNumber;
	
	@NotEmpty(message = "userAddress must not be empty")
	private String address;
	
	@NotEmpty(message = "email must not be empty")
    @Email(message = "email should be a valid email")
	@Pattern(regexp=".+@.+\\.[a-z]+")
	private String email;
	
	@NotEmpty(message = "password must not be empty")
	private String password;
	
	private String regMessage =" user registration failed";
	
	
	public String getRegMessage() {
		return regMessage;
	}
	public void setRegMessage(String regMessage) {
		this.regMessage = regMessage;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
