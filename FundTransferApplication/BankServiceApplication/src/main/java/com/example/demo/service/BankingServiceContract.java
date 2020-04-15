package com.example.demo.service;

import com.example.demo.entity.AccountDetailsEntity;
import com.example.demo.exception.InSufficientFundException;

public interface BankingServiceContract {
	
 public	String performFundTransferOperation(AccountDetailsEntity sourceUser, AccountDetailsEntity destinationUser,Double amount) throws InSufficientFundException;

}
