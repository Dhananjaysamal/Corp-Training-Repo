package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND , reason = " Found Duplicate Mobile number")
public class DuplicateMobileNumberException extends RuntimeException {

	private static final long serialVersionUID = -535345793656743673L;

	public DuplicateMobileNumberException(String message) {
		super(message);
	}
}
