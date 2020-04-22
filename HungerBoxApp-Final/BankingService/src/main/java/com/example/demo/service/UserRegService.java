package com.example.demo.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.User;
import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.UserRegistrationEntity;
import com.example.demo.functionalInterface.GenerarateAccountImpl;
import com.example.demo.functionalInterface.GenerateAccountNumber;
import com.example.demo.repository.AccountRepo;
import com.example.demo.repository.UserRegRepo;

@Service
public class UserRegService {

	@Autowired
	UserRegRepo userRegRepo;
	
	@Autowired
	AccountRepo accountRepo;
	
	@Autowired
	GenerateAccountNumber generarateAccountImpl;


	public UserRegistrationEntity saveRegisteredUserDetails(UserRegistrationEntity userRegistrationEntity,AccountEntity accountDetailsEntity) {
//		boolean status = userRegRepo.save(userRegistrationEntity)!=null;
//		boolean status1 = accountRepo.save(accountDetailsEntity)!=null;
//		return status && status1;
		return userRegRepo.save(userRegistrationEntity);
	}
	
	public AccountEntity saveExistUserAccountUpdateDetails(AccountEntity accountDetailsEntity,UserRegistrationEntity alreadyExistUserEntity) {
		accountDetailsEntity.setUserRegistrationEntity(alreadyExistUserEntity);
		return accountRepo.save(accountDetailsEntity);
	}

	public List<UserRegistrationEntity>	getAllUserAccountDetails(){
		return userRegRepo.findAll();
	}
	
	public List<AccountEntity> findAccountDetailsByMobileNumber(String mobileNo) {
		return accountRepo.findAccountDetailsByMobileNumber(mobileNo);
	}
	
	public UserRegistrationEntity findUserByMobileNumber(String mobileNumber) {
		return userRegRepo.findByMobileNumber(mobileNumber);
	}
	public List<AccountEntity> findByMobileAcNumbers(String mobileNumber){
		return accountRepo.findAccountDetailsByMobileNumber(mobileNumber);
	}
	public AccountEntity updateExistUserAccount(User user, AccountEntity accountDetailsEntity,UserRegistrationEntity alreadyExistUserEntity) {
		Integer i = userRegRepo.updateExistUserAccount(alreadyExistUserEntity.getUserId(), user.getFirstName(), user.getLastName(), user.getUserAddress(), user.getAccountBalance(), user.getEmail(), user.getPassword(), user.isAccountEnabled(), alreadyExistUserEntity.getMobileNumber());
		AccountEntity accountEntity = saveExistUserAccountUpdateDetails(accountDetailsEntity,alreadyExistUserEntity);
		return  accountEntity;
	}

	public AccountEntity  generateAccount(UserRegistrationEntity userRegistrationEntity,User user,String isAccountExist) throws ParseException{
		
		return generarateAccountImpl.generateAccount(userRegistrationEntity, user, isAccountExist);
	}

}
