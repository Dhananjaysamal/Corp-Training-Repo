package com.demo.service;

import com.demo.batch.entity.LoanDetailsEntity;
import com.demo.batch.entity.PaymentEntity;
import com.demo.bean.LoanAccountDTO;

public interface UserLoanAccountService {
	
	public LoanDetailsEntity applyLoan(LoanAccountDTO loanDto);
	public double calculateEmi(int months,Double amount, float interestRate);
	public PaymentEntity payEmi(int id,Double emi);
	public void updateBalanceAmount();
}
