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
@Table(name = "loan_details")
public class LoanDetailsEntity implements Serializable {
	
	
	private static final long serialVersionUID = 1870585303718049742L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO , generator = "loanSeq")
	 @SequenceGenerator(name="loanSeq", sequenceName="loanSeq", allocationSize=1)
	private int loanid;
	
	@Column(name="loanAmount", nullable = false)
	private Double loanAmount;
	
	@Column(name="balanceAmount", nullable = false)
	private Double balanceAmount;
	
	@Column(name="emiAmount", nullable = false)
	private Double emiAmount;
	
	@Column(name="tenure", nullable = false)
	private int tenure;
	
	
	private UserLoanAccountEntity userLoanAccountEntity;
	

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public UserLoanAccountEntity getUserLoanAccountEntity() {
		return userLoanAccountEntity;
	}

	public void setUserLoanAccountEntity(UserLoanAccountEntity userLoanAccountEntity) {
		this.userLoanAccountEntity = userLoanAccountEntity;
	}

	public int getLoanid() {
		return loanid;
	}

	public void setLoanid(int loanid) {
		this.loanid = loanid;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public Double getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(Double emiAmount) {
		this.emiAmount = emiAmount;
	}
	
	
}
