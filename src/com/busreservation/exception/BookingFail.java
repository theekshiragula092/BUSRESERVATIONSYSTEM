package com.busreservation.exception;

public class BookingFail extends Exception {

	public BookingFail() {
		super("Booking failed due to an unknown error");
		
	}
	
	public BookingFail(String message){
		super(message);
	}
}
