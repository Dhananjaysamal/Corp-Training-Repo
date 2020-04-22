package com.example.demo.feign.client;

import java.text.ParseException;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.bean.TransactionBean;
import com.example.demo.exception.AccountNotFoundException;


///@FeignClient(value = "orderservice", url = "${app.user.url}")
@FeignClient(value="bank-service", url = "http://localhost:9090/BankServiceApplication/")
public interface BankClient {
	@GetMapping("/")
	public void test();
	
	@GetMapping(value = "/{cardNumber}/{cvv}/{month}/{year}")
	public ResponseEntity<String> checkIfAccountExists(@PathVariable("cardNumber") String cardNumber,@PathVariable("cvv") int cvv,@PathVariable("month") int month,@PathVariable("year")int year) throws AccountNotFoundException;
	
	
	 @PostMapping(value = "/lastFiveTransaction/{accountNumber}")
		public List<TransactionBean> getLastFiveTransaction(@PathVariable("accountNumber") String accountNumber);
	 
	 
	 @PostMapping(value = "/{cardNumber}/{cvv}/{month}/{year}/{amount}/{toAccount}")
	 public String payment(RedirectAttributes  redirectAttributes,@PathVariable("cardNumber") String cardNumber,@PathVariable("cvv") int cvv,@PathVariable("month") int month,@PathVariable("year")int year,@PathVariable("amount")double amount,@PathVariable("toAccount") long toAccount) throws ParseException; 

}
