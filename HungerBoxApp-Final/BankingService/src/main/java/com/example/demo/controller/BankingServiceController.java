package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.bean.TransactionBean;
import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.TransactionEntity;
import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.exception.CardExpiryException;
import com.example.demo.exception.CardNumberInvalidException;
import com.example.demo.exception.InSufficientFundException;
import com.example.demo.log.LoggerMessage;
import com.example.demo.service.BankingService;

@RestController
@RequestMapping("")
public class BankingServiceController {
	
	@Autowired
	BankingService bankingService;
	
private final static Logger LOGGER = Logger.getLogger(BankingServiceController.class.getName());
	
	LoggerMessage loggerMessage = LoggerMessage.getLoggerMessageInstance();
	
	@GetMapping("/")
	public void test(){
		System.out.println(":::::::::::::::::::::::");
	}

	 @GetMapping(value = "/{fromAccount}/{toAccount}/{amount}")
	 public String transferFunds(@PathVariable("fromAccount") String fromAccount,@PathVariable("toAccount") String toAccount,@PathVariable("amount") String amount) throws AccountNotFoundException, InSufficientFundException
	 {
		 AccountEntity toAccountEntity=null;
		 AccountEntity fromAccountEntity=null;
		 try {
			  toAccountEntity = bankingService.findAccountDetailsByAccNumber(Long.parseLong(toAccount));
			  fromAccountEntity = bankingService.findAccountDetailsByAccNumber(Long.parseLong(fromAccount));
			
		 if(fromAccountEntity==null || toAccountEntity ==null ) {
			 LOGGER.info(loggerMessage.printLogMessage(() ->"Bank Account doesn't exist for the given Phone number"));
			 throw new AccountNotFoundException("Bank Account doesn't exist for the given Phone number");
		 }
			 
		 }catch (Exception e) {
			 LOGGER.info(loggerMessage.printLogMessage(() ->"Fund Transfer failed due to "+e.getMessage()));
			throw new AccountNotFoundException("Bank Account doesn't exist for the given Phone number");
		}
		 AccountEntity sourceAccount = fromAccountEntity;
		 AccountEntity destinationAccount=toAccountEntity;
		 return bankingService.performFundTransferOperation(sourceAccount,destinationAccount,Double.parseDouble(amount));
	 }
	 
	
	 public String transactionPerform(Long toAccount , Long fromAccount, Double amount) throws AccountNotFoundException, InSufficientFundException
	 {
		 AccountEntity toAccountEntity=null;
		 AccountEntity fromAccountEntity=null;
		 try {
			  toAccountEntity = bankingService.findAccountDetailsByAccNumber(toAccount);
			  fromAccountEntity = bankingService.findAccountDetailsByAccNumber(fromAccount);
			
		 if(fromAccountEntity==null || toAccountEntity ==null ) {
			 LOGGER.info(loggerMessage.printLogMessage(() ->"Bank Account doesn't exist for the given Phone number"));
			 throw new AccountNotFoundException("Bank Account doesn't exist for the given Phone number");
		 }
			 
		 }catch (Exception e) {
			 LOGGER.info(loggerMessage.printLogMessage(() ->"Fund Transfer failed due to "+e.getMessage()));
			throw new AccountNotFoundException("Bank Account doesn't exist for the given Phone number");
		}
		 AccountEntity sourceAccount = fromAccountEntity;
		 AccountEntity destinationAccount=toAccountEntity;
		 return bankingService.performFundTransferOperation(sourceAccount,destinationAccount,amount);
	 }
	 
	 
	 @PostMapping(value = "/{cardNumber}/{cvv}/{month}/{year}/{amount}/{toAccount}")
	 public String payment(RedirectAttributes  redirectAttributes,@PathVariable("cardNumber") String cardNumber,@PathVariable("cvv") int cvv,@PathVariable("month") int month,@PathVariable("year")int year,@PathVariable("amount")double amount,@PathVariable("toAccount") long toAccount) throws ParseException, AccountNotFoundException, InSufficientFundException {
		
		 AccountEntity accountEntity =	 bankingService.ValidateAccount(cardNumber, cvv, month, year);
		 if(accountEntity==null) {
			 throw new CardNumberInvalidException("Card number or cvv is invalid");
		 }
		 Date simpleDateFormat = new SimpleDateFormat( "yyyyMMdd" ).parse(year +""+month +30);
		if( accountEntity.getExpiryDate().after(simpleDateFormat)) {
			throw new CardExpiryException("Your card is expired in "+simpleDateFormat.getDate()+simpleDateFormat.getYear());
		}
		long fromAccount = accountEntity.getAccountNumber();
		redirectAttributes.addAttribute("fromAccount", fromAccount);
		redirectAttributes.addAttribute("toAccount", toAccount);
		redirectAttributes.addAttribute("amount", amount);
		
//		RedirectView rv = new RedirectView();
//        rv.setContextRelative(true);
//        rv.setUrl("/{fromAccount}/{toAccount}/{amount}");
        return transactionPerform(toAccount,fromAccount,amount);
	 }
	 
	 @GetMapping(value = "/{cardNumber}/{cvv}/{month}/{year}")
		public ResponseEntity<String> checkIfAccountExists(@PathVariable("cardNumber") String cardNumber,@PathVariable("cvv") int cvv,@PathVariable("month") int month,@PathVariable("year")int year) throws AccountNotFoundException
		{
		 AccountEntity accountDetailsEntitiy =  bankingService.findAccountDetailsByCardNumber(cardNumber);
			if(accountDetailsEntitiy==null)
			{
				throw new AccountNotFoundException("Bank Account doesn't exist for the given Phone number");
			}
			return new ResponseEntity<String>(HttpStatus.OK);
		}
	 
	 @PostMapping(value = "/lastFiveTransaction/{accountNumber}")
	public List<TransactionBean> getLastFiveTransaction(@PathVariable("accountNumber") String accountNumber){
		 List<TransactionEntity> transactionEntities =  bankingService.findTransactionDetailsByAccountNumber(Long.parseLong(accountNumber));
			if(transactionEntities==null)
			{
				throw new AccountNotFoundException("Bank Account doesn't exist for the given Phone number");
			}
			List<TransactionBean> transactionBeans = new ArrayList<>();
			transactionEntities.stream().forEach(entity ->{
				TransactionBean transactionBean = new TransactionBean();
				BeanUtils.copyProperties(entity, transactionBean);
				transactionBeans.add(transactionBean);
			});
			
		 return transactionBeans;
	 }
	 
}
