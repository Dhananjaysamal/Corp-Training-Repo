package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.bean.UserRegistrationBean;
import com.example.demo.entity.AccountDetailsEntity;
import com.example.demo.entity.UserRegistrationEntity;
import com.example.demo.exception.DuplicateMobileNumberException;
import com.example.demo.exception.UserAccountRegistrationFailException;
import com.example.demo.logger.LoggerMessage;
import com.example.demo.service.BankingService;
import com.example.demo.service.UserRegService;



@RestController
@RequestMapping(value = "/registration")
public class UserRegistrationController {
	
	@Autowired
	UserRegService userRegService;
	
	private final static Logger LOGGER = Logger.getLogger(UserRegistrationController.class.getName());
	
	LoggerMessage loggerMessage = LoggerMessage.getLoggerMessageInstance();
	
	 @GetMapping("/accountRegistrationForm")
	    public ModelAndView newContact(ModelAndView model) {
		 
	        UserRegistrationBean userRegistrationBean = new UserRegistrationBean();
	        model.addObject("userRegistrationBean", userRegistrationBean);
	        model.setViewName("UserRegistrationForm");
	        return model;
	    }
	 
	 
	 @PostMapping(value = "/saveUserDetails")
	    public ModelAndView saveEmployee(Model model,@ModelAttribute @Valid UserRegistrationBean userRegistrationBean , BindingResult result, RedirectAttributes redirectAttributes) {
		 
		 UserRegistrationEntity userRegistrationEntity = new UserRegistrationEntity();
		 
		 userRegistrationEntity.setFirstName(userRegistrationBean.getFirstName());
		 userRegistrationEntity.setLastName(userRegistrationBean.getLastName());
		 userRegistrationEntity.setUserAddress(userRegistrationBean.getUserAddress());
		 
		String encryptedPass = userRegService.CreateEncryptPassword(userRegistrationBean.getPassword());
		
		 userRegistrationEntity.setPassword(encryptedPass);
		 userRegistrationEntity.setEmail(userRegistrationBean.getEmail());
		 userRegistrationEntity.setAccountBalance(userRegistrationBean.getAccountBalance());
		 userRegistrationEntity.setAccountEnabled(userRegistrationBean.isAccountEnabled());
		 AccountDetailsEntity accountEntity =null;
		 accountEntity = userRegistrationBean.getAccountType().get(0).equals("Saving Account")?
				 userRegService.generateSavingAccountNumber(userRegistrationEntity,userRegistrationBean):
					 userRegService.generateCurrentAccountNumber(userRegistrationEntity,userRegistrationBean);
		
		 Set<AccountDetailsEntity> accountDetailsEntities = new HashSet<AccountDetailsEntity>();
		 accountDetailsEntities.add(accountEntity);
		 userRegistrationEntity.setAccounts(accountDetailsEntities);
		 userRegistrationEntity.setMobileNumber(userRegistrationBean.getMobileNumber());
		 if(result.hasErrors()) {
			 return new ModelAndView("UserRegistrationForm");
		 }
		 boolean isRegistrationSuccessful=false;
		 String mobileNo=null;
		 try {
			 UserRegistrationEntity alreadyExistUserEntity = userRegService.findUserByMobileNumber(userRegistrationBean.getMobileNumber());
			 isRegistrationSuccessful = alreadyExistUserEntity == null ?   userRegService.saveRegisteredUserDetails(userRegistrationEntity,accountEntity):
				 userRegService.updateExistUserAccount(alreadyExistUserEntity.getUserId(), userRegistrationBean.getFirstName(),
						 userRegistrationBean.getLastName(), userRegistrationBean.getUserAddress(), userRegistrationBean.getAccountBalance(), userRegistrationBean.getEmail(),
						 userRegistrationBean.getPassword(), userRegistrationBean.isAccountEnabled(), alreadyExistUserEntity.getMobileNumber(),accountEntity,alreadyExistUserEntity);
			 mobileNo = alreadyExistUserEntity !=null ?  alreadyExistUserEntity.getMobileNumber():userRegistrationEntity.getMobileNumber();
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
		 redirectAttributes.addFlashAttribute("mobileNo", mobileNo);
//		 model.addAttribute("mobileNo", mobileNo);
		return isRegistrationSuccessful ?  new ModelAndView("redirect:view"):  new ModelAndView("UserRegistrationForm");
		
	 }
	 
	 
	 @GetMapping(value = "/view")
	 public ModelAndView listEmployee(ModelAndView model) throws IOException {
		 // We can do it by using join query of user and account table also

		 try{
			 List<UserRegistrationEntity> listUserRegistrationEntities = userRegService.getAllUserAccountDetails();

			 List<UserRegistrationBean> listRegistrationBeans = new ArrayList<>();
			 listUserRegistrationEntities.forEach(userRegEntity ->{
				 UserRegistrationBean userRegistrationBean = new UserRegistrationBean();

				 userRegistrationBean.setFirstName(userRegEntity.getFirstName());
				 userRegistrationBean.setLastName(userRegEntity.getLastName());
				 userRegistrationBean.setUserAddress(userRegEntity.getUserAddress());
				 userRegistrationBean.setEmail(userRegEntity.getEmail());
				 userRegistrationBean.setAccountBalance(userRegEntity.getAccountBalance());
				 userRegistrationBean.setAccountEnabled(userRegEntity.isAccountEnabled());
				 userRegistrationBean.setMobileNumber(userRegEntity.getMobileNumber());

				 List<AccountDetailsEntity> accountDetailsEntities =  userRegService.findAccountDetailsByMobileNumber(userRegEntity.getMobileNumber());
				 Set<AccountDetailsEntity> accounts = userRegEntity.getAccounts();
				 Supplier<Stream<AccountDetailsEntity>> accountsSupplier = () ->accounts.stream();
				 List<Long> accountNumbers = new ArrayList<>();
				 accountsSupplier.get().forEach(s -> accountNumbers.add(s.getAccountNumber()));
				 userRegistrationBean.setAccountNumbers(accountNumbers);
				 //	        	String accountType = accountsSupplier.get().findFirst().get().getAccountType();

				 List<String> accountTypesOfSingleUser=   accountDetailsEntities.stream().map(m -> m.getAccountType()).collect(Collectors.toList());
				 userRegistrationBean.setAccountType(accountTypesOfSingleUser);

				 userRegistrationBean.setUserId(userRegEntity.getUserId());
				 userRegistrationBean.setPassword(userRegEntity.getPassword());
				 listRegistrationBeans.add(userRegistrationBean);
			 });


			 model.addObject("listRegistrationBeans", listRegistrationBeans);
			 model.setViewName("home");
		 }catch(Exception e) {
			 throw new UserAccountRegistrationFailException("Account registration is failed due to "+e.getMessage());
		 }
		 return model;
	 }
	 
}
