package com.busreservation.dao;

public class CustomerNotFound extends Exception {
    public CustomerNotFound() {
        super("Customer not found");
    }
    public CustomerNotFound(String message) {
        super(message);
    }
    // This class is used to handle exceptions related to customer not found scenarios

}
