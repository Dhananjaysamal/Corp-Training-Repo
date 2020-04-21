package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND , reason = " User Already  Exist Exception ")
public class AlreadyUserExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7813561138556756058L;
	
	
public	AlreadyUserExistException(String msg){
		super(msg);
	}
}
