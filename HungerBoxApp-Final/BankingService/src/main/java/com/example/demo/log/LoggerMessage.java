package com.example.demo.log;

import java.util.function.Supplier;

public class LoggerMessage {
	private static final LoggerMessage loggerInstance = new LoggerMessage();

	//private constructor to avoid client applications to use constructor
	private LoggerMessage(){}


	public String printLogMessage(Supplier<String> supplier){
		return supplier.get();
	}

	public static LoggerMessage getLoggerMessageInstance(){
		return loggerInstance;
	}

}
