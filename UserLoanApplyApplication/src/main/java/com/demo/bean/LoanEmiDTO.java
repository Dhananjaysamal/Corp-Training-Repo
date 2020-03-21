package com.demo.bean;

import java.io.Serializable;

public class LoanEmiDTO implements Serializable{
	
	private static final long serialVersionUID = -844108889408741162L;
	
	
	private int customerId;
	private int loanId;
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getLoanId() {
		return loanId;
	}
	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}
	
	

}
