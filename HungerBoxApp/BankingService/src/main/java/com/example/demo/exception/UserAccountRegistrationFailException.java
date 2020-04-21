package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND , reason = "UserAccountRegistration validation failed")
public class UserAccountRegistrationFailException extends RuntimeException{

	private static final long serialVersionUID = -243748070700713133L;

	public  UserAccountRegistrationFailException(String message){
		super(message);
	}
}
