package com.example.demo.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(AccountNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(AccountNotFoundException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Account details Not Found", details);
		logger.info("handleUserNotFoundException due to the reason " + details.get(0) +" when = "+request);
		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserAccountRegistrationFailException.class)
	public final ResponseEntity<Object> handleUserRegFailException(UserAccountRegistrationFailException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Account registration is failed", details);
		logger.info("handleUserRegFailException due to the reason " + details.get(0) +" when = "+request);
		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(DuplicateMobileNumberException.class)
	public final ResponseEntity<Object> handleDuplicateMobileNumberException(DuplicateMobileNumberException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Account registration is failed", details);
		logger.info("handleUserRegFailException due to the reason " + details.get(0) +" when = "+request);
		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ex.printStackTrace();
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Server Error", details);
		logger.info("handleAllExceptions due to the reason " + details +" when = "+request);
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
