package com.example.demo.service;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.bean.UserRegistrationBean;
import com.example.demo.entity.AccountDetailsEntity;
import com.example.demo.entity.UserRegistrationEntity;
import com.example.demo.repository.AccountRepo;
import com.example.demo.repository.UserRegRepo;

@Service
public class UserRegService {

	@Autowired
	UserRegRepo userRegRepo;
	
	@Autowired
	AccountRepo accountRepo;


	PasswordEncoder  passwordEncoder =
			PasswordEncoderFactories.createDelegatingPasswordEncoder(); 

	public boolean saveRegisteredUserDetails(UserRegistrationEntity userRegistrationEntity,AccountDetailsEntity accountDetailsEntity) {
		boolean status = userRegRepo.save(userRegistrationEntity)!=null;
		boolean status1 = accountRepo.save(accountDetailsEntity)!=null;
		return status && status1;
	}
	
	public boolean saveExistUserAccountUpdateDetails(AccountDetailsEntity accountDetailsEntity,UserRegistrationEntity alreadyExistUserEntity) {
		accountDetailsEntity.setUserRegistrationEntity(alreadyExistUserEntity);
		return accountRepo.save(accountDetailsEntity)!=null;
	}

	public List<UserRegistrationEntity>	getAllUserAccountDetails(){
		return userRegRepo.findAll();
	}
	
	public List<AccountDetailsEntity> findAccountDetailsByMobileNumber(String mobileNo) {
		return accountRepo.findAccountDetailsByMobileNumber(mobileNo);
	}
	
	public UserRegistrationEntity findUserByMobileNumber(String mobileNumber) {
		return userRegRepo.findByMobileNumber(mobileNumber);
	}
	
	public boolean updateExistUserAccount(int user_Id , String first_name ,String last_name,String user_address, Double account_balance,String email_address,String password, boolean Account_activate,String mobileNumber, AccountDetailsEntity accountDetailsEntity,UserRegistrationEntity alreadyExistUserEntity) {
		
		boolean isUserUpdated =  null!= userRegRepo.updateExistUserAccount(user_Id, first_name, last_name, user_address, account_balance, email_address, password, Account_activate, mobileNumber);
		 boolean isAccountDetailsUpdated = saveExistUserAccountUpdateDetails(accountDetailsEntity,alreadyExistUserEntity);
		return  isUserUpdated && isAccountDetailsUpdated;
	}

	public AccountDetailsEntity  generateSavingAccountNumber(UserRegistrationEntity userRegistrationEntity,UserRegistrationBean userRegistrationBean){

		//		Set<AccountDetailsEntity> accountDetailsEntities = new HashSet<AccountDetailsEntity>();
		//		AccountDetailsEntity savingAccountDetails = generateSavingAccount(userRegistrationEntity,userRegistrationBean);
		//		AccountDetailsEntity currentAccountDetails = generateCurrentAccount(userRegistrationEntity,userRegistrationBean);
		//
		//		accountDetailsEntities.add(savingAccountDetails);
		//		accountDetailsEntities.add(currentAccountDetails);

		return generateSavingAccount(userRegistrationEntity,userRegistrationBean);
	}
	public AccountDetailsEntity  generateCurrentAccountNumber(UserRegistrationEntity userRegistrationEntity,UserRegistrationBean userRegistrationBean){

		return generateCurrentAccount(userRegistrationEntity,userRegistrationBean);
	}
	private AccountDetailsEntity generateSavingAccount(UserRegistrationEntity userRegistrationEntity,UserRegistrationBean userRegistrationBean) {
		AccountDetailsEntity savingAccountDetails= new AccountDetailsEntity();
		savingAccountDetails.setAccountNumber(1l);
		String accountType =  userRegistrationBean.getAccountType().stream().findFirst().get();
		savingAccountDetails.setAccountType(accountType);
		savingAccountDetails.setBankName("SBI");
		savingAccountDetails.setMobileNumber(userRegistrationBean.getMobileNumber());
		savingAccountDetails.setAccountBalance(userRegistrationBean.getAccountBalance());
		savingAccountDetails.setUserRegistrationEntity(userRegistrationEntity);
		return savingAccountDetails;
	}

	public Predicate<String> isSavingAccount(){
		return s ->s.contentEquals("Saving Account");
	}
	public Predicate<String> isCurrentAccount(){
		return s ->s.contentEquals("Current Account");
	}
	private AccountDetailsEntity generateCurrentAccount(UserRegistrationEntity userRegistrationEntity,UserRegistrationBean userRegistrationBean) {
		AccountDetailsEntity currentAccountDetails= new AccountDetailsEntity();
		currentAccountDetails.setAccountNumber(2l);
		String accountType =  userRegistrationBean.getAccountType().stream().findFirst().get();
		currentAccountDetails.setAccountType(accountType);
		currentAccountDetails.setBankName("SBI");
		currentAccountDetails.setMobileNumber(userRegistrationBean.getMobileNumber());
		currentAccountDetails.setAccountBalance(userRegistrationBean.getAccountBalance());
		currentAccountDetails.setUserRegistrationEntity(userRegistrationEntity);
		return currentAccountDetails;
	}


	public String CreateEncryptPassword(String humanReadablePass){

		return passwordEncoder.encode(humanReadablePass);
	}

}
