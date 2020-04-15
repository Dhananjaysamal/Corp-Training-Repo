package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AccountDetailsEntity;
import com.example.demo.entity.TransactionEntity;
import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.exception.InSufficientFundException;
import com.example.demo.repository.AccountRepo;
import com.example.demo.repository.TransactionRepository;

@Service
public class BankingService implements BankingServiceContract{

	@Autowired
	AccountRepo accountRepo;

	@Autowired
	private TransactionRepository transactionRepository;

	public List<AccountDetailsEntity> findAccountDetailsByMobileNumber(String mobileNo)throws AccountNotFoundException{
		return accountRepo.findAccountDetailsByMobileNumber(mobileNo);
	}


	@Override
	public String performFundTransferOperation(AccountDetailsEntity srcAccount, AccountDetailsEntity destAccount,Double amount) throws InSufficientFundException
	{
		if(srcAccount.getAccountBalance()>=amount)
		{
			double remainingBal = srcAccount.getAccountBalance()-amount;
			srcAccount.setAccountBalance(remainingBal);
			accountRepo.save(srcAccount);

			double newBal = destAccount.getAccountBalance()+amount;
			destAccount.setAccountBalance(newBal);
			accountRepo.save(destAccount);

			TransactionEntity tx1 = new TransactionEntity();
			tx1.setAccountNumber(srcAccount.getAccountNumber());
			tx1.setPhoneNum(Long.parseLong(srcAccount.getMobileNumber()));
			tx1.setAmount(amount);
			tx1.setTransactionType("Debit");
			tx1.setDate(new Date().toString());

			TransactionEntity tx2 = new TransactionEntity();
			tx2.setAccountNumber(destAccount.getAccountNumber());
			tx2.setPhoneNum(Long.parseLong(destAccount.getMobileNumber()));
			tx2.setAmount(amount);
			tx2.setTransactionType("Credit");
			tx2.setDate(new Date().toString());

			transactionRepository.save(tx1);
			transactionRepository.save(tx2);
		}
		else
		{
			throw new InSufficientFundException("There is no sufficient funds to tranfer from given source account. Please try with a lesser amount.");
		}
		return "Funds transferred successfully";

	}

}
