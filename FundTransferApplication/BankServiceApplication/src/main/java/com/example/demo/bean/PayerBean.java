package com.example.demo.bean;

import org.springframework.stereotype.Component;

@Component
public class PayerBean {
	
	private String payerAccount;
	private String accountType;
	private Double transferAmount;
	private Double creditAmount;
	private Double debitAmount;
	private Double totalBalance;

}
