package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AccountEntity;
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

	public List<AccountEntity> findAccountDetailsByMobileNumber(String mobileNo)throws AccountNotFoundException{
		return accountRepo.findAccountDetailsByMobileNumber(mobileNo);
	}
	public AccountEntity  findAccountDetailsByCardNumber(String cardNumber) {
		return accountRepo.findAccountDetailsByCardNumber(cardNumber);
	}
	
	public List<TransactionEntity>  findTransactionDetailsByAccountNumber(Long accountNumber) {
		return transactionRepository.findTransactionDetailsByAccountNumber(accountNumber,PageRequest.of(0, 5, Sort.by("date")));
	}
	
	public AccountEntity  findAccountDetailsByAccNumber(Long accountNumber) {
		return accountRepo.findAccountDetailsByAccountNumber(accountNumber);
	}
	public AccountEntity ValidateAccount( String cardNumber,int cvv,int month,int year){
		return accountRepo.ValidateAccount(cardNumber, cvv);
	}

	@Override
	public String performFundTransferOperation(AccountEntity srcAccount, AccountEntity destAccount,Double amount) throws InSufficientFundException
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
