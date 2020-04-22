package com.example.demo.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.User;
import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.UserRegistrationEntity;
import com.example.demo.exception.DuplicateMobileNumberException;
import com.example.demo.exception.UserAccountRegistrationFailException;
import com.example.demo.log.LoggerMessage;
import com.example.demo.service.UserRegService;



@RestController
@RequestMapping("")
public class UserRegistrationController {
	
	@Autowired
	UserRegService userRegService;
	
	private final static Logger LOGGER = Logger.getLogger(UserRegistrationController.class.getName());
	
	LoggerMessage loggerMessage = LoggerMessage.getLoggerMessageInstance();

	@GetMapping("/info")
	 String getInfo() {
		 return "hello";
	 }
	 
	 @PostMapping(value = "/accountRegistration")
	    public @ResponseBody User saveEmployee(@RequestBody @Valid User user , BindingResult result) throws ParseException {
		 
		 UserRegistrationEntity userRegistrationEntity = new UserRegistrationEntity();
		 
		 userRegistrationEntity.setFirstName(user.getFirstName());
		 userRegistrationEntity.setLastName(user.getLastName());
		 userRegistrationEntity.setUserAddress(user.getUserAddress());
		 userRegistrationEntity.setPassword(user.getPassword());
		 userRegistrationEntity.setEmail(user.getEmail());
		 userRegistrationEntity.setAccountBalance(user.getAccountBalance());
		 userRegistrationEntity.setAccountEnabled(user.isAccountEnabled());
		 
		 AccountEntity accountEntity =null;
		 String isAccountExist="accountNotExist";
		
		 Set<AccountEntity> accountDetailsEntities = new HashSet<>();
		
		 userRegistrationEntity.setMobileNumber(user.getMobileNumber());
		 if(result.hasErrors()) {
			 throw new UserAccountRegistrationFailException("User registration validation fails due to :"+result.getAllErrors());
		 }
		 try {
			 UserRegistrationEntity alreadyExistUserEntity = userRegService.findUserByMobileNumber(user.getMobileNumber());
			 if( alreadyExistUserEntity == null ) {
				 accountEntity= userRegService.generateAccount(userRegistrationEntity, user,isAccountExist);
				 accountDetailsEntities.add(accountEntity);
				 userRegistrationEntity.setAccounts(accountDetailsEntities);
				 UserRegistrationEntity userRegistrationEntity2 =	 userRegService.saveRegisteredUserDetails(userRegistrationEntity,accountEntity);
				List<Long> accNoList = new ArrayList<Long>();
				List<String> accTypeList = new ArrayList<>();
				 userRegistrationEntity2.getAccounts().stream().forEach(p -> {
					 accNoList.add(p.getAccountNumber());
					 accTypeList.add(p.getAccountType());
				 } );
				 for (AccountEntity accEntity2 :  userRegistrationEntity2.getAccounts()) {
					 user.setCardNumber(accEntity2.getCardNumber());
					 user.setCvv(accEntity2.getCvv());
					 user.setExpDate(accEntity2.getExpiryDate());	
				 }
				 user.setUserId(userRegistrationEntity2.getUserId());
				 user.setAccountNumbers(accNoList);
				 user.setAccountType(accTypeList);
				
			 }else {
				 isAccountExist="accountExist";
				 accountEntity= userRegService.generateAccount(userRegistrationEntity, user,isAccountExist);
				
				for(AccountEntity accEntity:alreadyExistUserEntity.getAccounts()){
					accountEntity.setCardNumber(accEntity.getCardNumber());
					accountEntity.setCvv(accEntity.getCvv());
					accountEntity.setExpiryDate(accEntity.getExpiryDate());
				}
				
				 accountDetailsEntities.add(accountEntity);
				 userRegistrationEntity.setAccounts(accountDetailsEntities);
				AccountEntity accountEntity2 = userRegService.updateExistUserAccount(user,accountEntity,alreadyExistUserEntity);
			List<AccountEntity> accounts = 	userRegService.findByMobileAcNumbers(accountEntity2.getMobileNumber());
				List<Long> accNoList = new ArrayList<Long>();
				List<String> accTypeList = new ArrayList<>();
				accounts.stream().forEach(P ->{
					accNoList.add( P.getAccountNumber());
					accTypeList.add(P.getAccountType());
				});
				 user.setUserId(alreadyExistUserEntity.getUserId());
					 user.setAccountNumbers(accNoList);
					 user.setCardNumber(accountEntity2.getCardNumber());
					 user.setCvv(accountEntity2.getCvv());
					 user.setExpDate(accountEntity2.getExpiryDate());	
					 user.setAccountType(accTypeList);
			 }
				
		 }catch(Exception e) {
			 LOGGER.info(loggerMessage.printLogMessage(() ->" Registration details is not save in Bank DB  "+e.getMessage()));
			 if(e instanceof UserAccountRegistrationFailException) {
			 throw new UserAccountRegistrationFailException("User registration is failed due to "+e.getMessage());
			 }
			 else if(e instanceof DuplicateMobileNumberException) {
				 throw new DuplicateMobileNumberException("User registration is failed due to "+e.getMessage()); 
			 }else {
				 throw new RuntimeException("User registration is failed due to "+e.getMessage()); 
			 }
		 }
		
		 user.setIsRegSuccess("User Account Registration is success");
       return user;		
	 }
	 
}
