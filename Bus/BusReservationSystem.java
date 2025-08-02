import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

class Customer {
    String name, mobile, email, city;
    int age;
    int id;
    static int counter = 1000;

    Customer(String name, String mobile, String email, String city, int age) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.city = city;
        this.age = age;
        this.id = counter++;
    }

    @Override
    public String toString() {
        return "Customer ID: " + id + ", Name: " + name + ", Email: " + email;
    }

    public String getName() {
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
    }
}

class Bus {
    String busNumber, startPoint, endPoint, startTime;
    int totalSeats;
    double fare;
    boolean[] seats;

    Bus(String busNumber, int totalSeats, String startPoint, String endPoint, String startTime, double fare) {
        this.busNumber = busNumber;
        this.totalSeats = totalSeats;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startTime = startTime;
        this.fare = fare;
        this.seats = new boolean[totalSeats + 1]; // seat index starts from 1
    }

    public List<Integer> getAvailableSeats() {
        List<Integer> available = new ArrayList<>();
        for (int i = 1; i <= totalSeats; i++) {
            if (!seats[i]) available.add(i);
        }
        return available;
    }

    @Override
    public String toString() {
        return "Bus " + busNumber + " from " + startPoint + " to " + endPoint + ", Time: " + startTime + ", Fare: Rs." + fare;
    }

    public String getBusNumber() {
        throw new UnsupportedOperationException("Unimplemented method 'getBusNumber'");
    }
}

class Reservation {
    Customer customer;
    Bus bus;
    int seatNumber;

    Reservation(Customer customer, Bus bus, int seatNumber) {
        this.customer = customer;
        this.bus = bus;
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "Reservation: " + customer.name + " (ID: " + customer.id + ") on Bus " + bus.busNumber + ", Seat: " + seatNumber;
    }
}

public class BusReservationSystem {
    static Map<Integer, Customer> customers = new HashMap<>();
    static Map<String, Bus> buses = new HashMap<>();
    static List<Reservation> reservations = new ArrayList<>();
    static Map<String, Queue<Integer>> waitingQueue = new HashMap<>();
    static ReentrantLock lock = new ReentrantLock();

    // Registration
    public static Customer registerCustomer(String name, String mobile, String email, String city, int age) {
        Customer c = new Customer(name, mobile, email, city, age);
        customers.put(c.id, c);
        return c;
    }

    public static void registerBus(String number, int totalSeats, String from, String to, String time, double fare) {
        buses.put(number, new Bus(number, totalSeats, from, to, time, fare));
    }

    // Search
    public static List<Bus> searchBuses(String from, String to) {
        List<Bus> result = new ArrayList<>();
        for (Bus bus : buses.values()) {
            if (bus.startPoint.equalsIgnoreCase(from) && bus.endPoint.equalsIgnoreCase(to)) {
                result.add(bus);
            }
        }
        return result;
    }

    // Reserve
    public static void reserveSeat(int customerId, String busNumber, int seatNumber) {
        lock.lock();
        try {
            Customer customer = customers.get(customerId);
            Bus bus = buses.get(busNumber);
            if (bus.seats[seatNumber]) {
                System.out.println("Seat already booked. Adding to waiting list.");
                waitingQueue.computeIfAbsent(busNumber, k -> new LinkedList<>()).add(customerId);
            } else {
                bus.seats[seatNumber] = true;
                Reservation r = new Reservation(customer, bus, seatNumber);
                reservations.add(r);
                System.out.println("Reservation successful: " + r);
            }
        } finally {
            lock.unlock();
        }
    }

    // Cancel
    public static void cancelReservation(int customerId, String busNumber, int seatNumber) {
        lock.lock();
        try {
            Iterator<Reservation> it = reservations.iterator();
            while (it.hasNext()) {
                Reservation r = it.next();
                if (r.customer.id == customerId && r.bus.busNumber.equals(busNumber) && r.seatNumber == seatNumber) {
                    it.remove();
                    r.bus.seats[seatNumber] = false;
                    System.out.println("Reservation cancelled for seat " + seatNumber);
                    // Assign next in queue
                    Queue<Integer> queue = waitingQueue.get(busNumber);
                    if (queue != null && !queue.isEmpty()) {
                        int nextCustomerId = queue.poll();
                        reserveSeat(nextCustomerId, busNumber, seatNumber);
                    }
                    return;
                }
            }
            System.out.println("No matching reservation found.");
        } finally {
            lock.unlock();
        }
    }

    // Display
    public static void showAllReservations() {
        for (Reservation r : reservations) {
            System.out.println(r);
        }
    }

    // Seat Change Request
    public static void requestSeatChange(int customerId, String busNumber, int preferredSeat) {
        Queue<Integer> queue = waitingQueue.computeIfAbsent(busNumber, k -> new LinkedList<>());
        queue.add(customerId);
        System.out.println("Seat change request noted. You are added to the queue.");
    }

    // Main Method for Demonstration
    public static void main(String[] args) {
        Customer c1 = registerCustomer("Alice", "0711111111", "alice@mail.com", "Colombo", 25);
        Customer c2 = registerCustomer("Bob", "0722222222", "bob@mail.com", "Kandy", 30);

        registerBus("B100", 5, "Colombo", "Jaffna", "06:00 AM", 1500);
        registerBus("B101", 3, "Colombo", "Galle", "08:00 AM", 800);

        System.out.println("Available Buses from Colombo to Jaffna:");
        for (Bus b : searchBuses("Colombo", "Jaffna")) {
            System.out.println(b);
        }

        reserveSeat(c1.id, "B100", 1);
        reserveSeat(c2.id, "B100", 1); // Should go to waiting list

        cancelReservation(c1.id, "B100", 1); // Should reassign to Bob

        showAllReservations();
    }
}
