package com.busreservation.exception;

public class WrongCredentials extends Exception {

	public WrongCredentials() {
		setStackTrace(new StackTraceElement[0]); // Clear stack trace for security reasons
	}
	
	public WrongCredentials(String message){
		super(message);
	}
	
}
