package com.demo.batch.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="UserLoan")
public class UserLoanAccountEntity implements Serializable{
	
	
	
	private static final long serialVersionUID = -4297836516185661089L;
	@Id
	@Column(name="userId")
	@GeneratedValue(strategy = GenerationType.AUTO , generator = "userSeq")
	 @SequenceGenerator(name="userSeq", sequenceName="userSeq", allocationSize=1)
	private int userId;
	
	@Column(name="userName", nullable = false)
	private String userName;
	
	@Column(name="userPan" , nullable = false)
	private String userPan;
	
	@Column(name="userAdhara", nullable = false)
	private String userAdhara;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="userAge", nullable = false)
	private int age;
	
	@Column(name="userSalary", nullable = false)
	private int userSalary;
	
	
public UserLoanAccountEntity(String userName,String userPan,String userAdhara,String gender,int userSalary,int age) {
		
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
