package com.demo.batch.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "payment")
public class PaymentEntity implements Serializable{

	private static final long serialVersionUID = 2532478690228175689L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO , generator = "paymentSeq")
	 @SequenceGenerator(name="paymentSeq", sequenceName="paymentSeq", allocationSize=1)
	private int repaymentId;
	
	@Column(name="emiAmount", nullable = false)
	private double emiAmount;
	
	@Column(name="paymentDate", nullable = false)
	private Date paymentDate;
	
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid",nullable = false)
	private UserLoanAccountEntity userLoanAccountEntity;

	public int getRepaymentId() {
		return repaymentId;
	}

	public void setRepaymentId(int repaymentId) {
		this.repaymentId = repaymentId;
	}

	public double getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(double emiAmount) {
		this.emiAmount = emiAmount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public UserLoanAccountEntity getUserLoanAccountEntity() {
		return userLoanAccountEntity;
	}

	public void setUserLoanAccountEntity(UserLoanAccountEntity userLoanAccountEntity2) {
		this.userLoanAccountEntity = userLoanAccountEntity2;
	}
	

}
