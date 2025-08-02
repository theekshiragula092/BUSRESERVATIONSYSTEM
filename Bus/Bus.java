import java.util.ArrayList;
import java.util.List;
public class Bus {
    private String busNumber;
    private String route;
    private int totalSeats;
    private double fare;
    private boolean[] seats; // Represents the availability of seats. true if booked, false if available.
    @SuppressWarnings("unused")
    private int seatNumber;
    // These fields likely belong to a Reservation class, not the Bus class.
    // They are commented out or removed as they are causing compilation errors in this context. 
    public void Reservation(Customer customer, Bus bus, int seatNumber) { // This method name should likely be a constructor for a Reservation class.
        this.seatNumber = seatNumber;
        bus.seats[seatNumber] = true; // mark seat as booked
    }


    public Bus(String busNumber, String route) {
        this.busNumber = busNumber;
        this.route = route;
    }

    public String getBusNumber() {
        return busNumber;
    }
    public int getTotalSeats() {
        return totalSeats;
    }
    public double getFare() {
        return fare;
    }
    public boolean[] getSeats() {
        return seats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
        this.seats = new boolean[totalSeats + 1]; // seat index starts from 1
    }
    public void setFare(double fare) {
        this.fare = fare;
    }
    public List<Integer> getAvailableSeats() {
        List<Integer> available = new ArrayList<>();
        for (int i = 1; i <= totalSeats; i++) {
            if (!seats[i]) available.add(i);
        }
        return available;
    }
    
    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "busNumber='" + busNumber + '\'' +
                ", route='" + route + '\'' +
                '}';
    } // Closing brace for toString() method
    // The following lines seem to be misplaced and likely belong to another class or method.
    // this.seatNumber = seatNumber;
    // bus.seats[seatNumber] = true; // mark seat as booked
    }
