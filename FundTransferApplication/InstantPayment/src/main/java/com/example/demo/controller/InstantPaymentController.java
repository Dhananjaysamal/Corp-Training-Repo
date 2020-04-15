package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.PaymentClient;
import com.example.demo.entity.AccountEntity;
import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.service.PaymentService;

@RestController
@RequestMapping("")
public class InstantPaymentController {
	private static final String ACCOUNT_EXISTS_STATUS = "accounts exists";;
	private static final String FUNDS_TRANSFER_SUCCESS ="Funds transferred successfully";
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	private PaymentClient paymentClient;
	
	
	@GetMapping(value = "/{phoneNumber}")
	public String registerPhoneNumber(@PathVariable("phoneNumber") long phoneNumber)  throws AccountNotFoundException
	{
		  ResponseEntity<AccountEntity> accEntity = paymentClient.checkIfAccountExists(phoneNumber); 		  
		  long accountNumber = accEntity.getBody().getAccountNumber();
		  int statusCode =  accEntity.getStatusCodeValue();
		 
		 
		  if(statusCode==200)
			return paymentService.registerInInstaPay(phoneNumber,accountNumber);
		  throw new AccountNotFoundException("Bank Account doesn't exist for the given Phone number");
	}
	
	@PostMapping(value = "/{fromPhoneNum}/{toPhoneNum}/{amount}")
	public String transferFunds(@PathVariable long fromPhoneNum,@PathVariable long toPhoneNum,@PathVariable long amount)  throws AccountNotFoundException
	{
		String status =  paymentService.findByPhoneNumber(fromPhoneNum,toPhoneNum,amount);
		if(ACCOUNT_EXISTS_STATUS.equals(status))
		{
			status  = paymentClient.transferFunds(fromPhoneNum,toPhoneNum,amount);
		}
		if(FUNDS_TRANSFER_SUCCESS.equals(status))
		{
			paymentService.logTransactionDetails(fromPhoneNum,toPhoneNum,amount);
		}
		 return status;
	}

}
