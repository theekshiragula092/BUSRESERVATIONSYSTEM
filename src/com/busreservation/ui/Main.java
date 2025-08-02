package com.busreservation.ui;

import java.util.Scanner;

import com.busreservation.dao.colors.Colors;
import com.busreservation.methods.AdminMethods;
import com.busreservation.methods.CustomerMethods;
import com.busreservation.methods.SignUpCustomer;
import com.busreservation.dao.CustomerDao;
import com.busreservation.dao.CustomerDaoImpl;
import com.busreservation.dto.Customer;
import java.util.NoSuchElementException;
import com.busreservation.exception.SomeThingWentWrong;		
import java.util.InputMismatchException;
/**
 * Main class to start the Bus Reservation System
 * It provides options for customers and admins to access their respective portals
 */

public class Main {

	public static Scanner sc;

	public static void choiceCustomerOrAdmin() {
			
		System.out.println(Colors.BLACK_BOLD              +"+------------------------+"+"\n"
										                  +"| 1.ENTER AS CUSTOMER    |"+"\n"
								                          +"| 2.ENTER AS ADMIN       |"+"\n"
										                  +"| 3.EXIT                 |"+"\n"
										                  +"+------------------------+"+"\n"+Colors.RESET);
			
			
		ChoiceCustomer();
			
	}
	
	public static void ChoiceCustomer() {
		
        Scanner scanner = new Scanner(System.in); // Renamed 'sc' to 'scanner' to avoid duplication
		
		System.out.print(Colors.BLACK_BOLD+"Please choose a Number: "+Colors.RESET);
		
		int choice = 0;
		
		try {
			choice = scanner.nextInt(); // Use 'scanner' here
		} catch (NoSuchElementException e) { // Added InputMismatchException
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + "Input type should be number" + Colors.RESET);
			System.out.println("");
			choiceCustomerOrAdmin();
		}
		if(choice == 1) {

			customerMethods();
			
		}else if(choice == 2) {

			adminMethods(scanner); // Use 'scanner' here
			
		}else if(choice == 3) {
			
			System.out.println("");
			System.out.println(Colors.BLACK_ITALIC+"Thank You! Have a Great Day"+Colors.RESET);
		    sc.close(); // The 'sc' variable is not defined in this scope.
			System.exit(0);
		}else if(choice == 4) {
			System.out.println(Colors.RED_BACKGROUND + "Exiting the application..." + Colors.RESET);
			scanner.close(); // Close the scanner to avoid resource leak
			System.exit(0);
		}else if(choice == 5) {
			System.out.println(Colors.BLACK_BOLD + "Please enter a valid option." + Colors.RESET);
			choiceCustomerOrAdmin();
		}else if(choice == 6) {
			System.out.println(Colors.BLACK_BOLD + "Please enter a valid option." + Colors.RESET);
			choiceCustomerOrAdmin();			
			}else if(choice == 7) {	
			System.out.println(Colors.BLACK_BOLD + "Please enter a valid option." + Colors.RESET);
			choiceCustomerOrAdmin();
		}else if(choice == 8) {
			System.out.println(Colors.BLACK_BOLD + "Please enter a valid option." + Colors.BANANA_YELLOW_BACKGROUND + Colors.RESET);
			choiceCustomerOrAdmin();
		}else if(choice == 9) {

			scanner.close(); // Close the new scanner as well
		}else {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + "Please select a correct option" + Colors.RESET);
			System.out.println("");			
			System.out.println(Colors.BLACK_BOLD + "Please enter a valid option." + Colors.RESET);
			choiceCustomerOrAdmin();
		}
		
	}
	
	
	public static void customerMethods() {
		
		System.out.println("");
		System.out.println(Colors.BOXING+Colors.BLACK_BOLD+Colors.BANANA_YELLOW_BACKGROUND  +"   Customer Portal   "+Colors.RESET);
		
		System.out.println(Colors.BLACK_BOLD           
                                             +"+----------------------------------- +"+"\n"
		                                   	 +"| 1.Don't have Account? Sign Up      |"+"\n"
	                                         +"| 2.Login                            |"+"\n"
		                                     +"| 3.Back                             |"+"\n"
					                         +"+------------------------------------+"+"\n"+Colors.RESET);
		
		ChoiceCustomerTwo();
	}
	
	
	public static void ChoiceCustomerTwo(){
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print(Colors.BLACK_BOLD+"Your Choice: "+Colors.RESET);
		
		int choice = 0; 
		
		try {
			choice = sc.nextInt();
		}catch (InputMismatchException e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + "Input type should be number" + Colors.RESET);
			System.out.println("");
			customerMethods();
		}
		
		
		if(choice == 1) {
			
			 SignUpCustomer.signUpCustomer(sc);
			
		}else if(choice == 2) {

			 CustomerMethods.loginCustomer();
			
		}else if(choice == 3) {
			
			choiceCustomerOrAdmin();
			
		}else {
			
			System.out.println(Colors.RED_BACKGROUND+" Please select correct Option "+Colors.RESET);
			choiceCustomerOrAdmin();
		}
	}
	
	
	public static void adminMethods(Scanner sc) {
		
		System.out.println("");
		 System.out.println(Colors.BOXING+Colors.BLACK_BOLD+Colors.BANANA_YELLOW_BACKGROUND +"  Admin Portal  "+Colors.RESET);
		AdminMethods.adminLogin(sc);
		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.println(Colors.BLACK_BOLD+"---------------------------------------------"+Colors.RESET);
			System.out.print(Colors.BOXING+Colors.CYAN_BACKGROUND_BRIGHT+"     WELCOME ");
	     
			Thread.sleep(500);
	        System.out.print("TO ");
	        Thread.sleep(500);
	
	        System.out.print("XYZ Pvt.Ltd BUS ");
	        Thread.sleep(500);
	
	        System.out.print("RESERVATION ");
	        Thread.sleep(500);
	
	        System.out.print("SYSTEM     " + Colors.RESET);
	        Thread.sleep(500);
	
	        System.out.println();
	        System.out.println(Colors.BLACK_BOLD+"---------------------------------------------"+Colors.RESET);
	        System.out.println();
	        Thread.sleep(300);
	   
		} catch (InterruptedException e) {
				
				e.printStackTrace();
		}
		
		choiceCustomerOrAdmin();
		
        sc.close();
	}

	@Override
	public String toString() {
		return "Main []";
	}
	
}
