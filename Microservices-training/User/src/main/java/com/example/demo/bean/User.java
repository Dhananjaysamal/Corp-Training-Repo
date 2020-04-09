package com.example.demo.bean;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = -6747093212520283862L;
	
	private String name;
	private int userId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	

}
