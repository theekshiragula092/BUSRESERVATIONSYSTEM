// // ==================== CUSTOM EXCEPTION HIERARCHY ====================

// import java.util.regex.Pattern;
// @SuppressWarnings("unused")
// class BusReservationException extends Exception {
//     private String errorCode;
//     private long timestamp;
    
//     public BusReservationException(String message, String errorCode) {
//         super(message);
//         this.errorCode = errorCode;
//         this.timestamp = System.currentTimeMillis();
//     }
    
//     public String getErrorCode() { return errorCode; }
//     public long getTimestamp() { return timestamp; }
// }

// // Authentication and authorization exceptions
// class AuthenticationException extends BusReservationException {
//     public AuthenticationException(String message) {
//         super(message, "AUTH_001");
//     }
// }

// class AuthorizationException extends BusReservationException {
//     public AuthorizationException(String message) {
//         super(message, "AUTH_002");
//     }
// }

// // User-related exceptions
// class UserRegistrationException extends BusReservationException {
//     public UserRegistrationException(String message) {
//         super(message, "USER_001");
//     }
// }

// class UserValidationException extends BusReservationException {
//     public UserValidationException(String message) {
//         super(message, "USER_002");
//     }
// }

// // Bus-related exceptions
// class BusRegistrationException extends BusReservationException {
//     public BusRegistrationException(String message) {
//         super(message, "BUS_001");
//     }
// }

// class BusNotFoundException extends BusReservationException {
//     public BusNotFoundException(String message) {
//         super(message, "BUS_002");
//     }
// }

// class BusValidationException extends BusReservationException {
//     public BusValidationException(String message) {
//         super(message, "BUS_003");
//     }
// }

// // Reservation-related exceptions
// class SeatNotAvailableException extends BusReservationException {
//     public SeatNotAvailableException(String message) {
//         super(message, "SEAT_001");
//     }
// }

// class ReservationNotFoundException extends BusReservationException {
//     public ReservationNotFoundException(String message) {
//         super(message, "RSRV_001");
//     }
// }

// class ReservationValidationException extends BusReservationException {
//     public ReservationValidationException(String message) {
//         super(message, "RSRV_002");
//     }
// }

// class CancellationException extends BusReservationException {
//     public CancellationException(String message) {
//         super(message, "CANCEL_001");
//     }
// }

// // System-level exceptions
// class SystemException extends BusReservationException {
//     public SystemException(String message) {
//         super(message, "SYS_001");
//     }
// }

// class DataPersistenceException extends BusReservationException {
//     public DataPersistenceException(String message) {
//         // super(messge, "DATA_001");
//     }
// }

// class NotificationException extends BusReservationException {
//     public NotificationException(String message) {
//         super(message, "NOTIF_001");
//     }
// }

// // ==================== ERROR HANDLER UTILITY ====================

// import java.util.concurrent.ConcurrentHashMap;
// import java.util.logging.Level;
// import java.util.Map;

// class ErrorHandler {
//     private static final Logger logger = Logger.getLogger(ErrorHandler.class.getName());
//     private static final Map<String, String> errorMessages = new ConcurrentHashMap<>();
    
//     static {
//         // Initialize error message mappings
//         errorMessages.put("AUTH_001", "Authentication failed. Please check your credentials.");
//         errorMessages.put("AUTH_002", "Access denied. Insufficient permissions.");
//         errorMessages.put("USER_001", "User registration failed. Please verify your information.");
//         errorMessages.put("USER_002", "Invalid user data provided.");
//         errorMessages.put("BUS_001", "Bus registration failed. Please check bus details.");
//         errorMessages.put("BUS_002", "Bus not found in the system.");
//         errorMessages.put("BUS_003", "Invalid bus information provided.");
//         errorMessages.put("SEAT_001", "Seat is not available for reservation.");
//         errorMessages.put("RSRV_001", "Reservation not found in the system.");
//         errorMessages.put("RSRV_002", "Invalid reservation data provided.");
//         errorMessages.put("CANCEL_001", "Cancellation failed. Please try again.");
//         errorMessages.put("SYS_001", "System error occurred. Please contact support.");
//         errorMessages.put("DATA_001", "Data persistence error. Please try again.");
//         errorMessages.put("NOTIF_001", "Notification delivery failed.");
//     }
    
//     public static ErrorResponse handleException(Exception e) {
//         if (e instanceof BusReservationException) {
//             BusReservationException bre = (BusReservationException) e;
//             logger.log(Level.WARNING, "Business error: " + bre.getMessage(), bre);
//             return new ErrorResponse(
//                 bre.getErrorCode(),
//                 bre.getMessage(),
//                 errorMessages.getOrDefault(bre.getErrorCode(), "Unknown error"),
//                 bre.getTimestamp()
//             );
//         } else {
//             logger.log(Level.SEVERE, "Unexpected error: " + e.getMessage(), e);
//             return new ErrorResponse(
//                 "SYS_001",
//                 "System error occurred",
//                 "An unexpected error occurred. Please contact support.",
//                 System.currentTimeMillis()
//             );
//         }
//     }
    
//     public static void logError(String operation, String details, Exception e) {
//         logger.log(Level.SEVERE, 
//             String.format("Operation: %s, Details: %s, Error: %s", 
//                 operation, details, e.getMessage()), e);
//     }
    
//     public static void logWarning(String operation, String message) {
//         logger.log(Level.WARNING, 
//             String.format("Operation: %s, Warning: %s", operation, message));
//     }
// }

// // ==================== ERROR RESPONSE CLASS ====================

// class ErrorResponse {
//     private String errorCode;
//     private String message;
//     private String userMessage;
//     private long timestamp;
//     private boolean success;
    
//     public ErrorResponse(String errorCode, String message, String userMessage, long timestamp) {
//         this.errorCode = errorCode;
//         this.message = message;
//         this.userMessage = userMessage;
//         this.timestamp = timestamp;
//         this.success = false;
//     }
    
//     // Getters
//     public String getErrorCode() { return errorCode; }
//     public String getMessage() { return message; }
//     public String getUserMessage() { return userMessage; }
//     public long getTimestamp() { return timestamp; }
//     public boolean isSuccess() { return success; }
    
//     @Override
//     public String toString() {
//         return String.format("ErrorResponse{code='%s', message='%s', timestamp=%d}", 
//             errorCode, userMessage, timestamp);
//     }
// }

// // ==================== VALIDATION UTILITIES ====================


// class ValidationUtils {
//     private static final Pattern EMAIL_PATTERN = 
//         Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
//     private static final Pattern MOBILE_PATTERN = 
//         Pattern.compile("^[6-9]\\d{9}$");
//     private static final Pattern BUS_NUMBER_PATTERN = 
//         Pattern.compile("^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$");
    
//     public static void validateCustomerData(String name, String mobile, String email, 
//                                           String city, int age) throws UserValidationException {
//         if (name == null || name.trim().isEmpty()) {
//             throw new UserValidationException("Customer name is required");
//         }
        
//         if (name.length() < 2 || name.length() > 50) {
//             throw new UserValidationException("Customer name must be between 2 and 50 characters");
//         }
        
//         if (!MOBILE_PATTERN.matcher(mobile).matches()) {
//             throw new UserValidationException("Invalid mobile number format");
//         }
        
//         if (!EMAIL_PATTERN.matcher(email).matches()) {
//             throw new UserValidationException("Invalid email format");
//         }
        
//         if (city == null || city.trim().isEmpty()) {
//             throw new UserValidationException("City is required");
//         }
        
//         if (age < 1 || age > 120) {
//             throw new UserValidationException("Age must be between 1 and 120");
//         }
//     }
    
//     public static void validateBusData(String busNumber, int totalSeats, String startPoint, 
//                                      String endPoint, String startTime, double fare) 
//                                      throws BusValidationException {
//         if (busNumber == null || !BUS_NUMBER_PATTERN.matcher(busNumber).matches()) {
//             throw new BusValidationException("Invalid bus number format");
//         }
        
//         if (totalSeats < 1 || totalSeats > 100) {
//             throw new BusValidationException("Total seats must be between 1 and 100");
//         }
        
//         if (startPoint == null || startPoint.trim().isEmpty()) {
//             throw new BusValidationException("Starting point is required");
//         }
        
//         if (endPoint == null || endPoint.trim().isEmpty()) {
//             throw new BusValidationException("Ending point is required");
//         }
        
//         if (startPoint.equals(endPoint)) {
//             throw new BusValidationException("Starting point and ending point cannot be the same");
//         }
        
//         if (startTime == null || startTime.trim().isEmpty()) {
//             throw new BusValidationException("Starting time is required");
//         }
        
//         if (fare <= 0) {
//             throw new BusValidationException("Fare must be greater than zero");
//         }
//     }
    
//     public static void validateReservationData(String customerId, String busId, int seatNumber) 
//                                              throws ReservationValidationException {
//         if (customerId == null || customerId.trim().isEmpty()) {
//             throw new ReservationValidationException("Customer ID is required");
//         }
        
//         if (busId == null || busId.trim().isEmpty()) {
//             throw new ReservationValidationException("Bus ID is required");
//         }
        
//         if (seatNumber < 1) {
//             throw new ReservationValidationException("Invalid seat number");
//         }
//     }
// }

// // ==================== THREAD-SAFE OPERATION WRAPPER ====================

// import java.util.concurrent.locks.ReentrantLock;
// import java.util.concurrent.TimeUnit;
// import java.util.function.Supplier;

// class ThreadSafeOperationWrapper {
//     private static final ReentrantLock lock = new ReentrantLock();
//     private static final int LOCK_TIMEOUT_SECONDS = 30;
    
//     public static <T> T executeWithLock(Supplier<T> operation, String operationName) 
//                                        throws SystemException {
//         try {
//             if (lock.tryLock(LOCK_TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
//                 try {
//                     return operation.get();
//                 } finally {
//                     lock.unlock();
//                 }
//             } else {
//                 throw new SystemException("Operation timed out: " + operationName);
//             }
//         } catch (InterruptedException e) {
//             Thread.currentThread().interrupt();
//             throw new SystemException("Operation interrupted: " + operationName);
//         } catch (Exception e) {
//             if (e instanceof SystemException) {
//                 throw e;
//             }
//             throw new SystemException("Unexpected error in operation: " + operationName);
//         }
//     }
    
//     public static void executeWithLockVoid(Runnable operation, String operationName) 
//                                           throws SystemException {
//         executeWithLock(() -> {
//             operation.run();
//             return null;
//         }, operationName);
//     }
// }

// // ==================== EXAMPLE USAGE IN SERVICE METHODS ====================

// class ReservationService {
    
//     public ErrorResponse reserveSeat(String customerId, String busId, int seatNumber) {
//         try {
//             // Validate input data
//             ValidationUtils.validateReservationData(customerId, busId, seatNumber);
            
//             // Execute reservation with thread safety
//             ThreadSafeOperationWrapper.executeWithLockVoid(() -> {
//                 try {
//                     // Check seat availability
//                     if (!isSeatAvailable(busId, seatNumber)) {
//                         throw new SeatNotAvailableException("Seat " + seatNumber + " is already reserved");
//                     }
                    
//                     // Create reservation
//                     createReservation(customerId, busId, seatNumber);
                    
//                     // Send notification
//                     sendNotification(customerId, "Seat reserved successfully");
                    
//                     // Save to persistence
//                     saveReservation(customerId, busId, seatNumber);
                    
//                 } catch (Exception e) {
//                     if (e instanceof BusReservationException) {
//                         throw (RuntimeException) e;
//                     }
//                     throw new SystemException("Failed to reserve seat: " + e.getMessage());
//                 }
//             }, "SEAT_RESERVATION");
            
//             return new ErrorResponse("SUCCESS", "Seat reserved successfully", 
//                                    "Your seat has been reserved successfully", 
//                                    System.currentTimeMillis());
            
//         } catch (Exception e) {
//             return ErrorHandler.handleException(e);
//         }
//     }
    
//     public ErrorResponse cancelReservation(String reservationId) {
//         try {
//             ThreadSafeOperationWrapper.executeWithLockVoid(() -> {
//                 try {
//                     // Find reservation
//                     Reservation reservation = findReservation(reservationId);
//                     if (reservation == null) {
//                         throw new ReservationNotFoundException("Reservation not found: " + reservationId);
//                     }
                    
//                     // Cancel reservation
//                     cancelReservationInternal(reservationId);
                    
//                     // Notify customer
//                     sendNotification(reservation.getCustomerId(), "Reservation cancelled");
                    
//                     // Notify next customer in queue
//                     notifyNextCustomerInQueue(reservation.getBusId(), reservation.getSeatNumber());
                    
//                     // Update persistence
//                     updateReservationStatus(reservationId, "CANCELLED");
                    
//                 } catch (Exception e) {
//                     if (e instanceof BusReservationException) {
//                         throw (RuntimeException) e;
//                     }
//                     throw new CancellationException("Failed to cancel reservation: " + e.getMessage());
//                 }
//             }, "RESERVATION_CANCELLATION");
            
//             return new ErrorResponse("SUCCESS", "Reservation cancelled successfully", 
//                                    "Your reservation has been cancelled", 
//                                    System.currentTimeMillis());
            
//         } catch (Exception e) {
//             return ErrorHandler.handleException(e);
//         }
//     }
    
//     // Helper methods (simplified for demonstration)
//     private boolean isSeatAvailable(String busId, int seatNumber) {
//         // Implementation here
//         return true; // Placeholder
//     }
    
//     private void createReservation(String customerId, String busId, int seatNumber) {
//         // Implementation here
//     }
    
//     private void sendNotification(String customerId, String message) throws NotificationException {
//         // Implementation here
//         // If notification fails, throw NotificationException
//     }
    
//     private void saveReservation(String customerId, String busId, int seatNumber) 
//                                throws DataPersistenceException {
//         // Implementation here
//         // If save fails, throw DataPersistenceException
//     }
    
//     private Reservation findReservation(String reservationId) {
//         // Implementation here
//         return null; // Placeholder
//     }
    
//     private void cancelReservationInternal(String reservationId) {
//         // Implementation here
//     }
    
//     private void notifyNextCustomerInQueue(String busId, int seatNumber) {
//         // Implementation here
//     }
    
//     private void updateReservationStatus(String reservationId, String status) {
//         // Implementation here
//     }
// }

// // ==================== RESERVATION MODEL ====================

// class Reservation {
//     private String reservationId;
//     private String customerId;
//     private String busId;
//     private int seatNumber;
//     private String status;
    
//     // Constructor and getters
//     public Reservation(String reservationId, String customerId, String busId, int seatNumber) {
//         this.reservationId = reservationId;
//         this.customerId = customerId;
//         this.busId = busId;
//         this.seatNumber = seatNumber;
//         this.status = "ACTIVE";
//     }
    
//     public String getCustomerId() { return customerId; }
//     public String getBusId() { return busId; }
//     public int getSeatNumber() { return seatNumber; }
//     public String getStatus() { return status; }
// }
// ==================== CUSTOM EXCEPTION HIERARCHY ====================

// Base exception for all bus reservation system errors
class BusReservationException extends Exception {
    private String errorCode;
    private long timestamp;
    
    public BusReservationException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.timestamp = System.currentTimeMillis();
    }
    
    public String getErrorCode() { return errorCode; }
    public long getTimestamp() { return timestamp; }
}

// // Authentication and authorization exceptions
// class AuthenticationException extends BusReservationException {
//     public AuthenticationException(String message) {
//         super(message, "AUTH_001");
//     }
// }

class AuthorizationException extends BusReservationException {
    public AuthorizationException(String message) {
        super(message, "AUTH_002");
    }
}

// User-related exceptions
class UserRegistrationException extends BusReservationException {
    public UserRegistrationException(String message) {
        super(message, "USER_001");
    }
}

class UserValidationException extends BusReservationException {
    public UserValidationException(String message) {
        super(message, "USER_002");
    }
}

// Bus-related exceptions
class BusRegistrationException extends BusReservationException {
    public BusRegistrationException(String message) {
        super(message, "BUS_001");
    }
}

class BusNotFoundException extends BusReservationException {
    public BusNotFoundException(String message) {
        super(message, "BUS_002");
    }
}

class BusValidationException extends BusReservationException {
    public BusValidationException(String message) {
        super(message, "BUS_003");
    }
}

// Reservation-related exceptions
class SeatNotAvailableException extends BusReservationException {
    public SeatNotAvailableException(String message) {
        super(message, "SEAT_001");
    }
}

class ReservationNotFoundException extends BusReservationException {
    public ReservationNotFoundException(String message) {
        super(message, "RSRV_001");
    }
}

class ReservationValidationException extends BusReservationException {
    public ReservationValidationException(String message) {
        super(message, "RSRV_002");
    }
}

class CancellationException extends BusReservationException {
    public CancellationException(String message) {
        super(message, "CANCEL_001");
    }
}

// System-level exceptions
class SystemException extends BusReservationException {
    public SystemException(String message) {
        super(message, "SYS_001");
    }
}

class DataPersistenceException extends BusReservationException {
    public DataPersistenceException(String message) {
        super(message, "DATA_001");
    }
}

class NotificationException extends BusReservationException {
    public NotificationException(String message) {
        super(message, "NOTIF_001");
    }
}

// ==================== ERROR HANDLER UTILITY ====================

import java.util.logging.Logger;

import java.util.logging.Level;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

class ErrorHandler {
    private static final Logger logger = Logger.getLogger(ErrorHandler.class.getName());
    private static final Map<String, String> errorMessages = new ConcurrentHashMap<>();
    
    static {
        // Initialize error message mappings
        errorMessages.put("AUTH_001", "Authentication failed. Please check your credentials.");
        errorMessages.put("AUTH_002", "Access denied. Insufficient permissions.");
        errorMessages.put("USER_001", "User registration failed. Please verify your information.");
        errorMessages.put("USER_002", "Invalid user data provided.");
        errorMessages.put("BUS_001", "Bus registration failed. Please check bus details.");
        errorMessages.put("BUS_002", "Bus not found in the system.");
        errorMessages.put("BUS_003", "Invalid bus information provided.");
        errorMessages.put("SEAT_001", "Seat is not available for reservation.");
        errorMessages.put("RSRV_001", "Reservation not found in the system.");
        errorMessages.put("RSRV_002", "Invalid reservation data provided.");
        errorMessages.put("CANCEL_001", "Cancellation failed. Please try again.");
        errorMessages.put("SYS_001", "System error occurred. Please contact support.");
        errorMessages.put("DATA_001", "Data persistence error. Please try again.");
        errorMessages.put("NOTIF_001", "Notification delivery failed.");
    }
    
    public static ErrorResponse handleException(Exception e) {
        if (e instanceof BusReservationException) {
            BusReservationException bre = (BusReservationException) e;
            logger.log(Level.WARNING, "Business error: " + bre.getMessage(), bre);
            return new ErrorResponse(
                bre.getErrorCode(),
                bre.getMessage(),
                errorMessages.getOrDefault(bre.getErrorCode(), "Unknown error"),
                bre.getTimestamp()
            );
        } else {
            logger.log(Level.SEVERE, "Unexpected error: " + e.getMessage(), e);
            return new ErrorResponse(
                "SYS_001",
                "System error occurred",
                "An unexpected error occurred. Please contact support.",
                System.currentTimeMillis()
            );
        }
    }
    
    public static void logError(String operation, String details, Exception e) {
        logger.log(Level.SEVERE, 
            String.format("Operation: %s, Details: %s, Error: %s", 
                operation, details, e.getMessage()), e);
    }
    
    public static void logWarning(String operation, String message) {
        logger.log(Level.WARNING, 
            String.format("Operation: %s, Warning: %s", operation, message));
    }
}

// ==================== ERROR RESPONSE CLASS ====================

class ErrorResponse {
    private String errorCode;
    private String message;
    private String userMessage;
    private long timestamp;
    private boolean success;
    
    public ErrorResponse(String errorCode, String message, String userMessage, long timestamp) {
        this.errorCode = errorCode;
        this.message = message;
        this.userMessage = userMessage;
        this.timestamp = timestamp;
        this.success = false;
    }
    
    // Getters
    public String getErrorCode() { return errorCode; }
    public String getMessage() { return message; }
    public String getUserMessage() { return userMessage; }
    public long getTimestamp() { return timestamp; }
    public boolean isSuccess() { return success; }
    
    @Override
    public String toString() {
        return String.format("ErrorResponse{code='%s', message='%s', timestamp=%d}", 
            errorCode, userMessage, timestamp);
    }
}

// ==================== VALIDATION UTILITIES ====================

import java.util.regex.Pattern;

class ValidationUtils {
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern MOBILE_PATTERN = 
        Pattern.compile("^[6-9]\\d{9}$");
    private static final Pattern BUS_NUMBER_PATTERN = 
        Pattern.compile("^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$");
    
    public static void validateCustomerData(String name, String mobile, String email, 
                                          String city, int age) throws UserValidationException {
        if (name == null || name.trim().isEmpty()) {
            throw new UserValidationException("Customer name is required");
        }
        
        if (name.length() < 2 || name.length() > 50) {
            throw new UserValidationException("Customer name must be between 2 and 50 characters");
        }
        
        if (!MOBILE_PATTERN.matcher(mobile).matches()) {
            throw new UserValidationException("Invalid mobile number format");
        }
        
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new UserValidationException("Invalid email format");
        }
        
        if (city == null || city.trim().isEmpty()) {
            throw new UserValidationException("City is required");
        }
        
        if (age < 1 || age > 120) {
            throw new UserValidationException("Age must be between 1 and 120");
        }
    }
    
    public static void validateBusData(String busNumber, int totalSeats, String startPoint, 
                                     String endPoint, String startTime, double fare) 
                                     throws BusValidationException {
        if (busNumber == null || !BUS_NUMBER_PATTERN.matcher(busNumber).matches()) {
            throw new BusValidationException("Invalid bus number format");
        }
        
        if (totalSeats < 1 || totalSeats > 100) {
            throw new BusValidationException("Total seats must be between 1 and 100");
        }
        
        if (startPoint == null || startPoint.trim().isEmpty()) {
            throw new BusValidationException("Starting point is required");
        }
        
        if (endPoint == null || endPoint.trim().isEmpty()) {
            throw new BusValidationException("Ending point is required");
        }
        
        if (startPoint.equals(endPoint)) {
            throw new BusValidationException("Starting point and ending point cannot be the same");
        }
        
        if (startTime == null || startTime.trim().isEmpty()) {
            throw new BusValidationException("Starting time is required");
        }
        
        if (fare <= 0) {
            throw new BusValidationException("Fare must be greater than zero");
        }
    }
    
    public static void validateReservationData(String customerId, String busId, int seatNumber) 
                                             throws ReservationValidationException {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new ReservationValidationException("Customer ID is required");
        }
        
        if (busId == null || busId.trim().isEmpty()) {
            throw new ReservationValidationException("Bus ID is required");
        }
        
        if (seatNumber < 1) {
            throw new ReservationValidationException("Invalid seat number");
        }
    }
}

// ==================== THREAD-SAFE OPERATION WRAPPER ====================

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

class ThreadSafeOperationWrapper {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final int LOCK_TIMEOUT_SECONDS = 30;
    
    public static <T> T executeWithLock(Supplier<T> operation, String operationName) 
                                       throws SystemException {
        try {
            if (lock.tryLock(LOCK_TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
                try {
                    return operation.get();
                } finally {
                    lock.unlock();
                }
            } else {
                throw new SystemException("Operation timed out: " + operationName);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new SystemException("Operation interrupted: " + operationName);
        } catch (Exception e) {
            if (e instanceof SystemException) {
                throw e;
            }
            throw new SystemException("Unexpected error in operation: " + operationName);
        }
    }
    
    public static void executeWithLockVoid(Runnable operation, String operationName) 
                                          throws SystemException {
        executeWithLock(() -> {
            operation.run();
            return null;
        }, operationName);
    }
}

// ==================== EXAMPLE USAGE IN SERVICE METHODS ====================

class ReservationService {
    
    public ErrorResponse reserveSeat(String customerId, String busId, int seatNumber) {
        try {
            // Validate input data
            ValidationUtils.validateReservationData(customerId, busId, seatNumber);
            
            // Execute reservation with thread safety
            ThreadSafeOperationWrapper.executeWithLockVoid(() -> {
                try {
                    // Check seat availability
                    if (!isSeatAvailable(busId, seatNumber)) {
                        throw new SeatNotAvailableException("Seat " + seatNumber + " is already reserved");
                    }
                    
                    // Create reservation
                    createReservation(customerId, busId, seatNumber);
                    
                    // Send notification
                    sendNotification(customerId, "Seat reserved successfully");
                    
                    // Save to persistence
                    saveReservation(customerId, busId, seatNumber);
                    
                } catch (Exception e) {
                    if (e instanceof BusReservationException) {
                        throw (RuntimeException) e;
                    }
                    throw new SystemException("Failed to reserve seat: " + e.getMessage());
                }
            }, "SEAT_RESERVATION");
            
            return new ErrorResponse("SUCCESS", "Seat reserved successfully", 
                                   "Your seat has been reserved successfully", 
                                   System.currentTimeMillis());
            
        } catch (Exception e) {
            return ErrorHandler.handleException(e);
        }
    }
    
    public ErrorResponse cancelReservation(String reservationId) {
        try {
            ThreadSafeOperationWrapper.executeWithLockVoid(() -> {
                try {
                    // Find reservation
                    ErrorResponse reservation = cancelReservation(reservationId);
                    if (reservation == null) {
                        throw new ReservationNotFoundException("Reservation not found: " + reservationId);
                    }
                    
                    // Cancel reservation
                    cancelReservation(reservationId);
                    
                    // Notify customer
                    sendNotification(((Object) reservation).getCustomerId(), "Reservation cancelled");
                    
                    // Notify next customer in queue
                    notifyNextCustomerInQueue(reservation.getBusId(), reservation.getSeatNumber());
                    
                    // Update persistence
                    updateReservationStatus(reservationId, "CANCELLED");
                    
                } catch (Exception e) {
                    if (e instanceof BusReservationException) {
                        throw (RuntimeException) e;
                    }
                    throw new CancellationException("Failed to cancel reservation: " + e.getMessage());
                }
            }, "RESERVATION_C"
);
ReservationService service = new ReservationService();

// Reserve a seat
ErrorResponse response = service.reserveSeat("C001", "B001", 15);
if (response.isSuccess()) {
    System.out.println("Success: " + response.getMessage());
} else {
    System.out.println("Error: " + response.getUserMessage());
}

// Cancel reservation
ErrorResponse cancelResponse = service.cancelReservation("R001");

class Run {
    // This is the main class for the Bus Reservation System.
    
    public void main(String[] args) {
        // This is the entry point of the application.
        // You can initialize your bus reservation system here.
        System.out.println("Bus Reservation System is running...");
        // Initialize buses, customers, and other components as needed.
    }
    // Additional methods for bus reservation system operations can be added here.


    // For example, methods to add buses, make reservations, cancel reservations, etc.
    // These methods would interact with the Bus and Reservation classes to perform operations.     
    // Example:
    public void addBus(Bus bus) {
        // Logic to add a bus to the system
 System.out.println("Bus added: " + bus.busNumber);
    }
    public void makeReservation(Customer customer, Bus bus, int seatNumber) {
        // Logic to make a reservation
        System.out.println("Reservation made for customer: " + customer.getName() + " on bus: " + bus.getBusNumber() + " for seat: " + seatNumber);
    }
    public void cancelReservation(Customer customer, Bus bus, int seatNumber) {
        // Logic to cancel a reservation
        System.out.println("Reservation cancelled for customer: " + customer.getName() + " on bus: " + bus.getBusNumber() + " for seat: " + seatNumber);
    }
    public void showAvailableBuses() {
        // Logic to display all available buses
        System.out.println("Displaying all available buses...");
    }
}

    private void updateReservationStatus(String reservationId, String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateReservationStatus'");
    }