package com.demo.bean;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = -9024033358428575025L;

	private int userId;
	private String userName;
	private String userPan;
	private String userAdhara;
	private String gender;
	private int age;
	private int userSalary;
	
	public User(String userName,String userPan,String userAdhara,String gender,int userSalary,int age) {
		
		this.userAdhara=userAdhara;
		this.userName=userName;
		this.age=age;
		this.userPan=userPan;
		this.gender=gender;
		this.userSalary=userSalary;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPan() {
		return userPan;
	}
	public void setUserPan(String userPan) {
		this.userPan = userPan;
	}
	public String getUserAdhara() {
		return userAdhara;
	}
	public void setUserAdhara(String userAdhara) {
		this.userAdhara = userAdhara;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getUserSalary() {
		return userSalary;
	}
	public void setUserSalary(int userSalary) {
		this.userSalary = userSalary;
	}

}
