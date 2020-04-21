package com.example.demo.service;

import com.example.demo.entity.AccountEntity;
import com.example.demo.exception.InSufficientFundException;

public interface BankingServiceContract {
	
 public	String performFundTransferOperation(AccountEntity sourceUser, AccountEntity destinationUser,Double amount) throws InSufficientFundException;

}
