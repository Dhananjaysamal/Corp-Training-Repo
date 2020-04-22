package com.example.demo.functionalInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.springframework.stereotype.Component;

import com.example.demo.bean.User;
import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.UserRegistrationEntity;

@Component
public class GenerarateAccountImpl implements GenerateAccountNumber{

	public AccountEntity generateSpecificAccountNumber(UserRegistrationEntity userRegistrationEntity,User userRegistrationBean,String isAccountExist) throws ParseException {
		
		return  isSavingAccount(getAccountType(userRegistrationBean.getAccountType().get(0))).test(true) ?
		    generateSavingAccount(userRegistrationEntity,userRegistrationBean,isAccountExist):
			generateCurrentAccount(userRegistrationEntity,userRegistrationBean,isAccountExist);
	}
	
 	
	public Predicate<Boolean> isSavingAccount(Supplier<String> supl){
		return s -> supl.get().contentEquals("Saving Account");
	}
	public Predicate<String> isCurrentAccount(){
		return s ->s.contentEquals("Current Account");
	}
	
	private Supplier<String> getAccountType(String accountType){
		return  ()->accountType;
	}
	
	
	public  AccountEntity generateSavingAccount(UserRegistrationEntity userRegistrationEntity,User userRegistrationBean,String isAccountExist) throws ParseException {
		AccountEntity savingAccountDetails= new AccountEntity();
		savingAccountDetails.setAccountNumber(1l);
		String accountType =  userRegistrationBean.getAccountType().stream().findFirst().get();
		savingAccountDetails.setAccountType(accountType);
		savingAccountDetails.setBankName("SBI");
		savingAccountDetails.setMobileNumber(userRegistrationBean.getMobileNumber());
		savingAccountDetails.setAccountBalance(userRegistrationBean.getAccountBalance());
		if(isAccountExist.contentEquals("accountNotExist")) {
		savingAccountDetails.setCardNumber("");
		savingAccountDetails.setCvv(0);
		savingAccountDetails.setExpiryDate(new SimpleDateFormat( "yyyyMMdd" ).parse( "20301230" ));
		}
		savingAccountDetails.setUserRegistrationEntity(userRegistrationEntity);
		return savingAccountDetails;
	}
	
	
	public  AccountEntity generateCurrentAccount(UserRegistrationEntity userRegistrationEntity,User userRegistrationBean,String isAccountExist) throws ParseException {
		AccountEntity currentAccountDetails= new AccountEntity();
		currentAccountDetails.setAccountNumber(2l);
		String accountType =  userRegistrationBean.getAccountType().stream().findFirst().get();
		currentAccountDetails.setAccountType(accountType);
		currentAccountDetails.setBankName("SBI");
		currentAccountDetails.setMobileNumber(userRegistrationBean.getMobileNumber());
		currentAccountDetails.setAccountBalance(userRegistrationBean.getAccountBalance());
		if(isAccountExist.contentEquals("accountNotExist")) {
		currentAccountDetails.setCardNumber("");
		currentAccountDetails.setCvv(0);
		currentAccountDetails.setExpiryDate(new SimpleDateFormat( "yyyyMMdd" ).parse( "20301230" ));
		}
		currentAccountDetails.setUserRegistrationEntity(userRegistrationEntity);
		return currentAccountDetails;
	}

@Override
	public AccountEntity generateAccount(UserRegistrationEntity userRegistrationEntity, User user,String isAccountExist) throws ParseException {
		GenerateAccountNumber generateAccountNumber= (u,v,w)-> generateSpecificAccountNumber(userRegistrationEntity, user,isAccountExist);
		return generateAccountNumber.generateAccount(userRegistrationEntity, user,isAccountExist);
	}

}
