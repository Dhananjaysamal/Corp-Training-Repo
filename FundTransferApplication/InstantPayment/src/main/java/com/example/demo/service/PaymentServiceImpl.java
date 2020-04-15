package com.example.demo.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.TransactionEntity;
import com.example.demo.entity.UserRegistrationEntity;
import com.example.demo.repository.PaymentRepo;
import com.example.demo.repository.TransactionHistoryRepo;

public class PaymentServiceImpl implements PaymentService{
	@Autowired
	private PaymentRepo paymentRepo;
	
	@Autowired
	private TransactionHistoryRepo transactionHistoryRepo;

	@Override
	public String registerInInstaPay(long phoneNum,long accountNumber) {
		// TODO Auto-generated method stub
		UserRegistrationEntity userRegistrationEntity = new UserRegistrationEntity();
		userRegistrationEntity.setAccountNumber(accountNumber);
		userRegistrationEntity.setPhoneNum(phoneNum);
		
		Optional<UserRegistrationEntity> userOptional = paymentRepo.findByPhoneNum(phoneNum);
		if(userOptional.isPresent())
			return "Given Phone Number already Registered";
		else
		{
			paymentRepo.save(userRegistrationEntity);
			return "Phone Number Successfully Registered";
		}
		
	}

	@Override
	public String findByPhoneNumber(long fromPhoneNum, long toPhoneNum, long amount) 
	{
		Optional<UserRegistrationEntity> fromPh = paymentRepo.findByPhoneNum(fromPhoneNum);
		if(fromPh.isPresent())
		{
			Optional<UserRegistrationEntity> toPh = paymentRepo.findByPhoneNum(toPhoneNum);
			if(toPh.isPresent())
			{
				return "accounts exists";
			}
			else
			{
				return "phone number is registered  to perform the transaction ";
			}
		}
		else
		{
			return "phone number is registered  to perform the transaction ";
		}

	}

	@Override
	public void logTransactionDetails(long fromPhoneNum, long toPhoneNum, long amount) 
	{
		TransactionEntity tx1 = new TransactionEntity();
		tx1.setPhoneNumber(fromPhoneNum);
		tx1.setAmount(amount);
		tx1.setTransactionType("Debit");
		tx1.setDate(new Date().toString());
		
		TransactionEntity tx2 = new TransactionEntity();
		tx2.setPhoneNumber(toPhoneNum);
		tx2.setAmount(amount);
		tx2.setTransactionType("Credit");
		tx2.setDate(new Date().toString());
		
		transactionHistoryRepo.save(tx1);
		transactionHistoryRepo.save(tx2);
		
	}
}
