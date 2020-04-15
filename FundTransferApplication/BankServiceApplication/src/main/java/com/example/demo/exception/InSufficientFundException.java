package com.example.demo.exception;

public class InSufficientFundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8692242299369852317L;

	public InSufficientFundException(String msg){
		super(msg);
	}
}
