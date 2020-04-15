package com.example.demo.service;

public interface PaymentService {
	String registerInInstaPay(long phoneNum,long accountNumber);

	String findByPhoneNumber(long fromPhoneNum, long toPhoneNum, long amount);

	void logTransactionDetails(long fromPhoneNum, long toPhoneNum, long amount);
}
