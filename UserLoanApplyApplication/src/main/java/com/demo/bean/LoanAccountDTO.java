package com.demo.bean;

import java.io.Serializable;

public class LoanAccountDTO implements Serializable{
	
	private static final long serialVersionUID = 3071903269245154583L;
	
	private int tenureInMonths;
	private Double loanAmount;
	private int customerId;
	
	public LoanAccountDTO(int tenureInMonths, Double loanAmount, int customerId) {
		this.tenureInMonths = tenureInMonths;
		this.loanAmount = loanAmount;
		this.customerId = customerId;
	}
	public int getTenureInMonths() {
		return tenureInMonths;
	}
	public void setTenureInMonths(int tenureInMonths) {
		this.tenureInMonths = tenureInMonths;
	}
	public Double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	
	

}
