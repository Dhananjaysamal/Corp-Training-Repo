package com.example.demo.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AccountDetailsEntity;
import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.exception.InSufficientFundException;
import com.example.demo.logger.LoggerMessage;
import com.example.demo.service.BankingService;

@RestController
@RequestMapping(value = "/bankingService")
public class BankingServiceController {
	
	@Autowired
	BankingService bankingService;
	
private final static Logger LOGGER = Logger.getLogger(BankingServiceController.class.getName());
	
	LoggerMessage loggerMessage = LoggerMessage.getLoggerMessageInstance();
	
	@GetMapping("/")
	public void test(){
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
	}

	 @PostMapping(value = "/{fromMobileNumber}/{toMobileNumber}/{amount}")
	 public String transferFunds(@PathVariable("fromMobileNumber") Long fromMobileNumber,@PathVariable("toMobileNumber") Long toMobileNumber,@PathVariable("amount") Long amount) throws AccountNotFoundException, InSufficientFundException
	 {
		 System.out.println(" transferFunds:::::::::::::::::");
		 List<AccountDetailsEntity> fromAccounts =null;
		 List<AccountDetailsEntity> toAccounts=null;
		 try {
		  fromAccounts = bankingService.findAccountDetailsByMobileNumber(fromMobileNumber.toString());
		 toAccounts = bankingService.findAccountDetailsByMobileNumber(toMobileNumber.toString());
		 if(fromAccounts==null || toAccounts ==null ) {
			 LOGGER.info(loggerMessage.printLogMessage(() ->"Bank Account doesn't exist for the given Phone number"));
			 throw new AccountNotFoundException("Bank Account doesn't exist for the given Phone number");
		 }
			 
		 }catch (Exception e) {
			 LOGGER.info(loggerMessage.printLogMessage(() ->"Fund Transfer failed due to "+e.getMessage()));
			throw new AccountNotFoundException("Bank Account doesn't exist for the given Phone number");
		}
		 AccountDetailsEntity sourceAccount = fromAccounts.get(0);
		 AccountDetailsEntity destinationAccount=toAccounts.get(0);
		 return bankingService.performFundTransferOperation(sourceAccount,destinationAccount,Double.parseDouble(amount.toString()));
	 }
	 
	 @GetMapping(value = "/{phoneNum}")
		public ResponseEntity<AccountDetailsEntity> checkIfAccountExists(@PathVariable long phoneNum) throws AccountNotFoundException
		{
		 List<AccountDetailsEntity> accountDetailsEntities =  bankingService.findAccountDetailsByMobileNumber(String.valueOf(phoneNum));
			if(accountDetailsEntities.get(0)==null)
			{
				throw new AccountNotFoundException("Bank Account doesn't exist for the given Phone number");
			}
			return new ResponseEntity<AccountDetailsEntity>(HttpStatus.OK);
		}
	 
}
