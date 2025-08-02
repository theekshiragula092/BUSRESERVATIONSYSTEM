package com.busreservation.exception;

public class SomeThingWentWrong extends Exception {

	public SomeThingWentWrong() {
		super("Something went wrong, please try again later");
	}
	
	public SomeThingWentWrong(String message){
		super(message);
	}
	
}
