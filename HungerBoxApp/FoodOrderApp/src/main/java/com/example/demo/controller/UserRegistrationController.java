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
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.AlreadyUserExistException;
import com.example.demo.exception.DuplicateMobileNumberException;
import com.example.demo.exception.UserAccountRegistrationFailException;
import com.example.demo.service.UserRegService;



@RestController
@RequestMapping("")
public class UserRegistrationController {
	
	@Autowired
	UserRegService userRegService;
	
	private final static Logger LOGGER = Logger.getLogger(UserRegistrationController.class.getName());
	

	 @PostMapping(value = "/accountRegistration")
	    public @ResponseBody User saveEmployee(@RequestBody @Valid User user , BindingResult result) throws ParseException {
		 
		 UserEntity userEntity = new UserEntity();
		 
		 userEntity.setName(user.getName());
		 userEntity.setAddress(user.getAddress());
		 userEntity.setEmail(user.getEmail());
		 userEntity.setMobileNumber(user.getMobileNumber());
		 userEntity.setPassword(user.getPassword());
		 
		 if(result.hasErrors()) {
			 throw new UserAccountRegistrationFailException("User registration validation fails due to :"+result.getAllErrors());
		 }
		 try {
			 UserEntity alreadyExistUserEntity = userRegService.findUserByMobileNumber(user.getMobileNumber());
			 if(alreadyExistUserEntity!=null) {
				 throw new AlreadyUserExistException("User Already exist in this system");
			 }else {
				 userRegService.saveRegisteredUserDetails(userEntity);
			 }
				
		 }catch(Exception e) {
			 LOGGER.info(" Registration details is not save in Bank DB  "+e.getMessage());
			 if(e instanceof UserAccountRegistrationFailException) {
			 throw new UserAccountRegistrationFailException("User registration is failed due to "+e.getMessage());
			 }
			 else if(e instanceof DuplicateMobileNumberException) {
				 throw new DuplicateMobileNumberException("User registration is failed due to "+e.getMessage()); 
			 }else {
				 throw new RuntimeException("User registration is failed due to "+e.getMessage()); 
			 }
		 }
		 user.setRegMessage("User Account Registration is success");
       return user;		
	 }
	 
}
