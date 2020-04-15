package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.AccountEntity;
import com.example.demo.exception.AccountNotFoundException;

//@FeignClient(value = "paymentService" , url ="${app.user.url}" )
@FeignClient(name = "http://bank-service/BankServiceApplication/bankingService/")
public interface PaymentClient {
	 @PostMapping(value = "/{fromMobileNumber}/{toMobileNumber}/{amount}")
	 public String transferFunds(@PathVariable("fromMobileNumber") Long fromMobileNumber,@PathVariable("toMobileNumber") Long toMobileNumber,@PathVariable("amount") Long amount);

	 @GetMapping(value = "/{phoneNum}")
		public ResponseEntity<AccountEntity> checkIfAccountExists(@PathVariable long phoneNum) throws AccountNotFoundException;

}
