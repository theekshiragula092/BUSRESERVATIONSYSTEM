package com.busreservation.methods;

import java.util.InputMismatchException; // Corrected import statement
import java.util.List;
import java.util.Scanner;

import com.busreservation.dao.BusBookingDao;
import com.busreservation.dao.BusBookingDaoImpl;
import com.busreservation.dao.BusInfoDao;
import com.busreservation.dao.BusInfoDaoImpl;
import com.busreservation.dao.CustomerDao;
import com.busreservation.dao.CustomerDaoImpl;
import com.busreservation.dao.colors.Colors;
import com.busreservation.dto.Ticket;
import com.busreservation.exception.CustomerNotFound;
import com.busreservation.exception.NoTicketFound;
import com.busreservation.exception.SomeThingWentWrong;
import com.busreservation.exception.WrongCredentials;
import com.busreservation.ui.Main;

public class AdminMethods {

    public static int count = 1;

    public static void adminLogin(Scanner sc) {
        BusInfoDao admin = new BusInfoDaoImpl();

        if (count == 2 || count == 3) {
            System.out.println(Colors.YELLOW_BACKGROUND + " You have " + (4 - count) + " attempts left! " + Colors.RESET);
        }

        System.out.println();
        System.out.println(Colors.BLACK_BOLD + "Please Enter your username" + Colors.RESET);

        String username;
        try {
            username = sc.next();
        } catch (InputMismatchException e) {
            System.out.println();
            System.out.println(Colors.RED_BACKGROUND + " Input type should be String " + Colors.RESET);
            sc.nextLine(); // clear buffer
            adminLogin(sc);
            return;
        }

        System.out.println(Colors.BLACK_BOLD + "Please Enter your Password" + Colors.RESET);
        String password;

        try {
            password = sc.next();
        } catch (InputMismatchException e) {
            System.out.println();
            System.out.println(Colors.RED_BACKGROUND + " Input type should be String " + Colors.RESET);
            sc.nextLine(); // clear buffer
            adminLogin(sc);
            return;
        }

        try {
            if (admin.loginAdmin(username, password)) {
                System.out.println();
                System.out.println(Colors.BOXING + Colors.BLACK_BOLD + Colors.BANANA_YELLOW_BACKGROUND + "  Welcome Admin  " + Colors.RESET);
                AdminDashboard.adminDashboard();
            }
        } catch (WrongCredentials e) {
            System.out.println();
            System.out.println(Colors.RED_BACKGROUND + " Wrong Credentials " + Colors.RESET);
            count++;
            if (count == 4) {
                System.out.println(Colors.RED_BOLD + "You've reached the maximum amount of login attempts!" + Colors.RESET);
                count = 0;
                Main.choiceCustomerOrAdmin();
                return;
            }
            Main.choiceCustomerOrAdmin();
        }
    }

    public static void getAllTicketList() {
        BusBookingDao busBooking = new BusBookingDaoImpl();

        try {
            List<Ticket> list = busBooking.getAllTicketList();
            list.forEach(i -> System.out.println(Colors.PURPLE_BOLD + i + Colors.RESET));
            Thread.sleep(2000);
        } catch (NoTicketFound e) {
            System.out.println(Colors.YELLOW_BACKGROUND + " No Ticket Found " + Colors.RESET);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        AdminDashboard.adminDashboard();
    }

    public static void getAllPendingReq() {
        BusBookingDao busBooking = new BusBookingDaoImpl();

        try {
            busBooking.getPedingTicketReq().forEach(i -> {
                System.out.println(Colors.PURPLE_BOLD + i + Colors.RESET);
            });

            Thread.sleep(2000);
        } catch (NoTicketFound e) {
            System.out.println(Colors.YELLOW_BACKGROUND + " No Record Found " + Colors.RESET);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        AdminDashboard.adminDashboard();
    }

    public static void confirmAllPendingReq(Scanner sc) {
        BusBookingDao busBooking = new BusBookingDaoImpl();

        try {
            if (busBooking.confirmAllPendingReq()) {
                Thread.sleep(2000);
                System.out.println();
                System.out.println(Colors.GREEN_BACKGROUND + " All Tickets are Confirmed " + Colors.RESET);
            }
        } catch (SomeThingWentWrong e) {
            System.out.println(Colors.YELLOW_BACKGROUND + " No Record Found " + Colors.RESET);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        AdminDashboard.adminDashboard();
    }

    public static void confirmByTicketNo() {
        try (Scanner sc = new Scanner(System.in)) {
			BusBookingDao busBooking = new BusBookingDaoImpl();

			System.out.println(Colors.BLACK_BOLD + "Enter Ticket Number" + Colors.RESET);
			int ticketNumber = 0;

			try {
			    ticketNumber = sc.nextInt();
			} catch (InputMismatchException e) {
			    System.out.println();
			    System.out.println(Colors.RED_BACKGROUND + " Input type should be Number " + Colors.RESET);
			    sc.nextLine(); // clear buffer
			    confirmByTicketNo();
			    return;
			}

			try {
			    if (busBooking.confirmTicket(ticketNumber)) {
			        Thread.sleep(2000);
			        System.out.println();
			        System.out.println(Colors.GREEN_BACKGROUND + " Ticket is Confirmed Now " + Colors.RESET);
			    }
			} catch (NoTicketFound e) {
			    System.out.println();
			    System.out.println(Colors.RED_BACKGROUND + " No Ticket Found " + Colors.RESET);
			} catch (InterruptedException e) {
			    e.printStackTrace();
			}
		}
        System.out.println();
        AdminDashboard.adminDashboard();
    }

    public static void customerInfo() {
        CustomerDao customers = new CustomerDaoImpl();

        try {
            customers.getCustomersInfo().forEach(i -> {
                System.out.println(Colors.PURPLE_BOLD + i + Colors.RESET);
            });

            Thread.sleep(2000);
        } catch (CustomerNotFound e) {
            System.out.println();
            System.out.println(Colors.RED_BACKGROUND + " No Customer Found " + Colors.RESET);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        AdminDashboard.adminDashboard();
    }

	public static void customerProfile() {
		try (Scanner sc = new Scanner(System.in)) {
			CustomerDao customers = new CustomerDaoImpl();

			System.out.println(Colors.BLACK_BOLD + "Enter Customer ID" + Colors.RESET);
			int customerId = 0;

			try {
				customerId = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println(Colors.RED_BACKGROUND + " Input type should be Number " + Colors.RESET);
				sc.nextLine(); // clear buffer
				customerProfile();
				return;
			}

			System.out.println(Colors.PURPLE_BOLD + customers.getCustomerById(customerId) + Colors.RESET);
		}
		System.out.println();
		AdminDashboard.adminDashboard();
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		AdminMethods.count = count;
	}

	@Override
	public String toString() {
		return "AdminMethods []";
	}
}