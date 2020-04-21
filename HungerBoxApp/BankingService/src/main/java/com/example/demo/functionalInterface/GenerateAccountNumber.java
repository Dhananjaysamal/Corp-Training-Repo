package com.example.demo.functionalInterface;

import java.text.ParseException;

import com.example.demo.bean.User;
import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.UserRegistrationEntity;

@FunctionalInterface
public interface GenerateAccountNumber {

	public abstract AccountEntity generateAccount(UserRegistrationEntity userRegistrationEntity, User user,String isAccountExist) throws ParseException;
	
	public default void generateCardNumber() {
		
	}
	
	public default AccountEntity getAccountEntity(AccountEntity accountEntity){
		return accountEntity;
	}

}
