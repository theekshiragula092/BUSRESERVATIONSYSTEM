// package com.busreservation.methods;

// import java.util.Scanner;

// import com.busreservation.colors.Colors;
// import com.busreservation.dao.CustomerDao;
// import com.busreservation.dao.CustomerDaoImpl;
// import com.busreservation.dto.Customer;
// import com.busreservation.exception.SomeThingWentWrong;

// public class CustomerMethods {

//     static Scanner sc = new Scanner(System.in);

//     public static void registerCustomer() {
//         System.out.println(Colors.PURPLE_BOLD + "======== CUSTOMER REGISTRATION ========" + Colors.RESET);

//         System.out.print("Enter First Name: ");
//         String fname = sc.next();

//         System.out.print("Enter Last Name: ");
//         String lname = sc.next();

//         System.out.print("Enter Email: ");
//         String email = sc.next();

//         System.out.print("Enter Username: ");
//         String username = sc.next();

//         System.out.print("Enter Password: ");
//         String password = sc.next();

//         System.out.print("Enter Address: ");
//         sc.nextLine(); // consume leftover newline
//         String address = sc.nextLine();

//         System.out.print("Enter Mobile Number: ");
//         String mobile = sc.next();

//         Customer customer = new Customer();
//         customer.setFirstName(fname);
//         customer.setLastName(lname);
//         customer.setEmail(email);
//         customer.setUsername(username);
//         customer.setPassword(password);
//         customer.setAddress(address);
//         customer.setMobile(mobile);

//         CustomerDao dao = new CustomerDaoImpl();

//         try {
//             dao.registerCustomer(customer);
//             System.out.println(Colors.GREEN_BOLD + "✅ Registration successful!" + Colors.RESET);
//         } catch (SomeThingWentWrong e) {
//             System.out.println(Colors.RED_BOLD + "❌ Error: " + e.getMessage() + Colors.RESET);
//         }
//     }

//     public static void loginCustomer() {
//         System.out.println(Colors.PURPLE_BOLD + "======== CUSTOMER LOGIN ========" + Colors.RESET);

//         System.out.print("Enter Username: ");
//         String username = sc.next();

//         System.out.print("Enter Password: ");
//         String password = sc.next();

//         CustomerDao dao = new CustomerDaoImpl();

//         try {
//             dao.loginCustomer(username, password);
//             System.out.println(Colors.GREEN_BOLD + "✅ Login successful!" + Colors.RESET);
//             // Add next menu or dashboard here
//         } catch (SomeThingWentWrong e) {
//             System.out.println(Colors.RED_BOLD + "❌ Error: " + e.getMessage() + Colors.RESET);
//         }
//     }

//     public static void changePassword() {
      
//         throw new UnsupportedOperationException("Unimplemented method 'changePassword'");
//     }

//     public static void customerProfile() {
    
//         throw new UnsupportedOperationException("Unimplemented method 'customerProfile'");
//     }

//     public static void searchBusByDestination(Scanner sc2) {
   
//         throw new UnsupportedOperationException("Unimplemented method 'searchBusByDestination'");
//     }

//     public static void bookBusTicket() {
//         throw new UnsupportedOperationException("Unimplemented method 'bookBusTicket'");
//     }

//     public static void checkTicket(Scanner sc2) {
//         throw new UnsupportedOperationException("Unimplemented method 'checkTicket'");
//     }

//     public static void cancelTicket() {
//         throw new UnsupportedOperationException("Unimplemented method 'cancelTicket'");
//     }
// }
package com.busreservation.methods;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.busreservation.dao.BusBookingDao;
import com.busreservation.dao.BusBookingDaoImpl;
import com.busreservation.dao.BusInfoDaoImpl;
import com.busreservation.dao.CustomerDaoImpl;
import com.busreservation.dto.BusInfo;
import com.busreservation.dto.Customer;
import com.busreservation.dto.Ticket;
import com.busreservation.exception.BookingFail;
import com.busreservation.exception.CustomerNotFound;
import com.busreservation.exception.NoTicketFound;
import com.busreservation.dao.colors.Colors;
import com.busreservation.exception.SomeThingWentWrong;
import com.busreservation.exception.WrongCredentials;
import com.busreservation.ui.Main;

public class CustomerMethods {

	public static int count = 1;

	/**
	 * Customer Details will be Saved Below After login
	 */
	public static int cusID;
	public static String fname;
	public static String lname;
	public static String mobile;
	public static String email;
	public static String password;

    @SuppressWarnings("unused")
    private static Ticket[] tickets;
	
	public static void loginCustomer() {
         
		 try (Scanner sc = new Scanner(System.in)) {
			CustomerDaoImpl Customer = new CustomerDaoImpl();
			
			 if(count==2) {
				 System.out.println(Colors.YELLOW_BACKGROUND+" You have "+ (4-count) +" attempts left! "+Colors.RESET);
			 }else if(count==3) {
				 System.out.println(Colors.YELLOW_BACKGROUND+" You have "+ (4-count) +" attempts left! "+Colors.RESET);
			 }
			 
			 System.out.println("");
			 System.out.println(Colors.BLACK_BOLD + "Please Enter your Email" + Colors.RESET);
			 String emailAddress = null;
			 
			 try {
				 emailAddress = sc.next();
			 }catch (InputMismatchException e) {
			 	System.out.println("");
			 	System.out.println(Colors.RED_BACKGROUND + "Input type should be String" + Colors.RESET);
			 	System.out.println("");
			 	loginCustomer();
			 }
			 
			 System.out.println(Colors.BLACK_BOLD +"Please Enter your Password"+ Colors.RESET);
			 String pass = null;
			 
			 try {
				 pass = sc.next();
			 }catch (InputMismatchException e) {
			 	System.out.println("");
			 	System.out.println(Colors.RED_BACKGROUND + " Input type should be String " + Colors.RESET);
			 	System.out.println("");
			 	loginCustomer();
			 }
			 
			if(Customer.customerLogin(emailAddress, pass) != null) {	
				Customer customer = (com.busreservation.dto.Customer) Customer.customerLogin(emailAddress, pass);
				System.out.println("");
				System.out.println(Colors.BOXING+Colors.BLACK_BOLD+Colors.YELLOW_BACKGROUND+"  Welcome "+ customer.getfName() + " " +customer.getlName()+"  " + Colors.RESET);
				System.out.println("");
				
				cusID = customer.getCustomerId();
				fname = customer.getfName();
				lname = customer.getlName();
				mobile = customer.getMobile();
				email = customer.getEmail();
				password = customer.getPassword();
				
				CustomerDashboard.customerChoice();
				
			}
		 } catch (SomeThingWentWrong e) {
            e.printStackTrace();
        } catch (WrongCredentials e) {
            e.printStackTrace();
        }
		
	}
	
	
	public static void customerProfile() {
		
		Scanner sc = new Scanner(System.in);
		
		CustomerDaoImpl customer = new CustomerDaoImpl();
		Customer cus = null;
		System.out.println("");
		System.out.println(Colors.BLACK_BOLD+"Please Enter your Email"+Colors.RESET);
		String email = null;
		
		try {
			email = sc.next();
		}catch (InputMismatchException e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + "Input type should be String" + Colors.RESET);
			System.out.println("");
			sc.close();
			customerProfile();
			return;
		}
		
		
		try {
			
			cus = customer.getMyDetails(email,cusID);
			System.out.println("");
			System.out.println(Colors.PURPLE_BOLD  +"  CustomerId: " + cus.getCustomerId()        );
			System.out.println(Colors.PURPLE_BOLD  +"  Name: " + cus.getfName()+" "+cus.getlName());
			System.out.println(Colors.PURPLE_BOLD  +"  Email: " + cus.getEmail()                  );
			System.out.println(Colors.PURPLE_BOLD  +"  Password: " + cus.getPassword()            );
			System.out.println(Colors.PURPLE_BOLD  +"  Mobile: " + cus.getMobile()                );
			System.out.println(""); 
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				
				System.out.println(Colors.RED_BACKGROUND+" Wrong Email Address "+Colors.RESET);
			}
			
			sc.close();
			CustomerDashboard.customerChoice();
			
		} catch (CustomerNotFound e) {
		
			System.out.println(Colors.RED_BACKGROUND+" Customer Not Found "+Colors.RESET);
			sc.close();
			CustomerDashboard.customerChoice();
			
		}
		
	}
	
	
	public static void changePassword() {
		
		Scanner sc = new Scanner(System.in);
		
		CustomerDaoImpl customer = new CustomerDaoImpl();
		
		System.out.println(Colors.BLACK_BOLD + "Enter your Email "+Colors.RESET);
		String emailAdd = sc.next();
		
		System.out.println(Colors.BLACK_BOLD+"Enter your New Password "+Colors.RESET);
		String pass = sc.next();
		
		try {
			customer.changeCustomerPassword(emailAdd, pass);
			password = pass;
			System.out.println("");
			System.out.println(Colors.GREEN_BACKGROUND+"Password Changed Successfully"+Colors.RESET);
			System.out.println("");
			sc.close();
			CustomerDashboard.customerChoice();
		} catch (CustomerNotFound | SomeThingWentWrong e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + " Customer Not Found " + Colors.RESET);
			System.out.println("");
			sc.close();
			CustomerDashboard.customerChoice();
		}
		
	}
	
	
	public static void bookBusTicket() {
		
		try (Scanner sc = new Scanner(System.in)) {
			BusBookingDaoImpl busBook = new BusBookingDaoImpl();
			
			System.out.println("");
			System.out.println(Colors.BLACK_BOLD +"Please Enter your Bus Number"+Colors.RESET);
			int busNo = 0;
			
			try {
				busNo = sc.nextInt();
			}catch (InputMismatchException e) {
				System.out.println("");
				System.out.println(Colors.RED_BACKGROUND + " Input type should be Number " + Colors.RESET);
				System.out.println("");
				bookBusTicket();
			}
			
			System.out.println(Colors.BLACK_BOLD + "Please Enter Ticket Quantity"+Colors.RESET);
			int ticketAmount = 0;
			
			try {
				ticketAmount = sc.nextInt();
			}catch (InputMismatchException e) {
				System.out.println("");
				System.out.println(Colors.RED_BACKGROUND + " Input type should be Number " + Colors.RESET);
				System.out.println("");
				bookBusTicket();
			}
			
			try {
				
				if(busBook.bookTicket(cusID, busNo, ticketAmount)) {
					System.out.println("");
				    System.out.println(Colors.YELLOW_BACKGROUND + " Ticket Booked Successfully wait for Confirmation from Admin "+Colors.RESET);
				    System.out.println("");
				    CustomerDashboard.customerChoice();
				}
				
			} catch (BookingFail e) {
				System.out.println("");
				System.out.println(Colors.RED_BACKGROUND + " Booking Fail " + Colors.RESET);
				System.out.println("");
				CustomerDashboard.customerChoice();
				
			}
		}
		
	}
	
	
	public static void searchBusByDestination(Scanner sc) throws SomeThingWentWrong {
		
		BusInfoDaoImpl buses = new BusInfoDaoImpl();
		List<BusInfo> busList = new ArrayList<>();
		System.out.println("");
		System.out.println(Colors.BLACK_BOLD+"Bus Pickup From"+Colors.RESET);
		String dep = sc.next();
		System.out.println(Colors.BLACK_BOLD +"Bus Drop location"+Colors.RESET);
		String arr = sc.next();
		
		busList = buses.searchBusByDestination(dep, arr);
		
		busList.forEach(i->{
			System.out.println("");
			System.out.println(Colors.PURPLE_BOLD + i + Colors.RESET)  ;
			System.out.println("");
		});
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		CustomerDashboard.customerChoice();
		
	}

    @SuppressWarnings("unused")
    private Object busreservation;
	
	public static void checkTicket(Scanner sc){
		
		BusBookingDao busBooking = (BusBookingDao) new BusBookingDaoImpl(); 
		
		
		System.out.println("");
		System.out.println(Colors.BLACK_BOLD + "Please Enter Bus Number" + Colors.RESET);
		int busNumber = 0;
		
		try {
			busNumber = sc.nextInt();
		}catch (InputMismatchException e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + " Input type should be Number " + Colors.RESET);
			System.out.println("");
			bookBusTicket();
		}
		
		try {
			List<Ticket> tickets = busBooking.getTicket(cusID, busNumber);
			
			for(Ticket ticket: tickets) {
				
				System.out.println("");
				System.out.println(Colors.PURPLE_BOLD + " Ticket Number : " + ((Ticket) ticket).getTicketNo() + Colors.RESET);
				System.out.println(Colors.PURPLE_BOLD + " Customer ID : " + ((Customer) ticket).getCustomerId() + Colors.RESET);
				System.out.println(Colors.PURPLE_BOLD + " Bus Number : " + ((Ticket) ticket).getBusNumber() + Colors.RESET);
				System.out.println(Colors.PURPLE_BOLD + " Date of Booking : " + ((Ticket) ticket).getDateOfBooking() + Colors.RESET);
				System.out.println(Colors.PURPLE_BOLD + " Date of Departure : " + ((Ticket) ticket).getDeparture() + Colors.RESET);
				System.out.println(Colors.PURPLE_BOLD + " Total tickets : " + ((Ticket) ticket).getTotal_tickets() + Colors.RESET);
				System.out.println(Colors.PURPLE_BOLD + " Total Fare : " + ((Ticket) ticket).getTotal_fare() + Colors.RESET);
				if (ticket.isStatus()) {
					System.out.println(Colors.PURPLE_BOLD + " Status : Booked"  + Colors.RESET);
				}else {
					System.out.println(Colors.PURPLE_BOLD + " Status : Pending" + Colors.RESET);
				}

				System.out.println("");
				
			}
			
			
		    
			
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
			CustomerDashboard.customerChoice();
			
		} catch (NoTicketFound e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND+" No Ticket Found "+ Colors.RESET);
			System.out.println("");
			CustomerDashboard.customerChoice();
		}
	}
	
	public static void cancelTicket(){
		// Scanner sc  = new Scanner(System.in); // This scanner is already declared and closed in loginCustomer()
		Scanner sc = new Scanner(System.in); // Create a new scanner for this method
		BusBookingDao busBooking = (BusBookingDao) new BusBookingDaoImpl(); 
		System.out.println("");
		System.out.println(Colors.BLACK_BOLD+ "Please Enter Ticket Number"+Colors.RESET);
		
		int ticketNumber = 0;
		
		try {
			ticketNumber = sc.nextInt();
		}catch (InputMismatchException e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + "Input type should be Number" + Colors.RESET);
			System.out.println("");
			sc.close(); // Close the scanner before recursive call
			cancelTicket();
			return;
		}
		
		System.out.println(Colors.BLACK_BOLD+ "Please Enter Bus Number"+Colors.RESET);
			
		int busNumber = 0;
		
		try {
			busNumber = sc.nextInt();
		}catch (InputMismatchException e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + "Input type should be Number" + Colors.RESET);
			System.out.println("");
			sc.close(); // Close the scanner before recursive call
			cancelTicket();
			return;
		}
		
		System.out.println(Colors.BLACK_BOLD+ "Please Enter Number of Tickets"+Colors.RESET);
		int tickets = 0;
		
		try {
			tickets = sc.nextInt();
		}catch (InputMismatchException e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + "Input type should be Number" + Colors.RESET);
			System.out.println("");
			sc.close(); // Close the scanner before recursive call
			cancelTicket();
			return;
		}
		
		try {
			
			if(busBooking.cancelBooking(ticketNumber , cusID , busNumber , tickets)) {
				System.out.println("");
				System.out.println(Colors.GREEN_BACKGROUND+" Ticket Cancel Success " + Colors.RESET);
				System.out.println("");
			}
			
			sc.close(); // Close the scanner after use
			CustomerDashboard.customerChoice();
		} catch (SomeThingWentWrong e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND+" You cannot cancel Bus Ticket "+ Colors.RESET);
			System.out.println("");
			sc.close(); // Close the scanner after use
			CustomerDashboard.customerChoice(); // Return to dashboard
		}
		
	}

}