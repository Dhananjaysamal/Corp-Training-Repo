package com.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.batch.entity.LoanDetailsEntity;
import com.demo.batch.entity.PaymentEntity;
import com.demo.batch.entity.UserLoanAccountEntity;
import com.demo.bean.LoanAccountDTO;
import com.demo.repository.CustomerRepository;
import com.demo.repository.LoanDetailsRepository;
import com.demo.repository.PaymentRepo;
import com.demo.repository.UserLoanAccountRepo;

@Service
public class UserLoanAccountServiceImpl implements UserLoanAccountService{
	@Autowired
	UserLoanAccountRepo userLoanAccountRepo;
	
	@Autowired
	LoanDetailsRepository loanDetailsRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	PaymentRepo paymentRepo;
	
	public List<UserLoanAccountEntity> saveUserLoanAccountDetails(List<UserLoanAccountEntity> userLoanAccounts) {
		List<UserLoanAccountEntity> list = new ArrayList<UserLoanAccountEntity>();
		 userLoanAccounts.forEach(userLoanAccount -> list.add(userLoanAccountRepo.save(userLoanAccount)));
		 
		 return list;
	}

	@Override
	public LoanDetailsEntity applyLoan(LoanAccountDTO loanDto) {
		
		int tenureInMonths = loanDto.getTenureInMonths();
		Double loanAmount = loanDto.getLoanAmount();
		float rateOfInterest = 5;
		LoanDetailsEntity loanDetails = new LoanDetailsEntity();
		loanDetails.setEmiAmount(calculateEmi(tenureInMonths, loanAmount, rateOfInterest));
		UserLoanAccountEntity userLoanAccountEntity= userLoanAccountRepo.findById(loanDto.getCustomerId());
		loanDetails.setUserLoanAccountEntity(userLoanAccountEntity);
		loanDetails.setLoanAmount(loanAmount);
		loanDetails.setBalanceAmount(loanAmount);
		loanDetails.setTenure(tenureInMonths);
		return loanDetailsRepository.save(loanDetails);
		
	}
	
	public double calculateEmi(int months,Double amount, float interestRate) {
		double emi; 
	      
		interestRate = interestRate / (12 * 100); // one month interest 
        emi = (amount * interestRate * (float)Math.pow(1 + interestRate, months))  
                / (float)(Math.pow(1 + interestRate, months) - 1); 
        return emi;
	}

	@Override
	public PaymentEntity payEmi(int id,Double emi) {
		PaymentEntity repayment = new PaymentEntity();
		java.util.Optional<UserLoanAccountEntity> userLoanAccountEntity=customerRepository.findById(id);
		repayment.setUserLoanAccountEntity(userLoanAccountEntity.get());
		repayment.setEmiAmount(emi);
		repayment.setPaymentDate(new Date());
		return paymentRepo.save(repayment);
	}

	@Override
	public void updateBalanceAmount() {
		
		
	}

}
