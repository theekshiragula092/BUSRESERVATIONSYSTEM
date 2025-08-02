package com.busreservation.exception;

public class NoTicketFound extends Exception {

	public NoTicketFound() {
		super("No ticket found for the given criteria");
		
	}
	
	public NoTicketFound(String message) {
		super(message);
	}
	
}
