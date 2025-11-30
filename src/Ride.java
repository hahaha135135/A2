/**
 * Ride class representing theme park rides/attractions
 * Manages ride information and assigned operator
 */
public class Ride {
    // Instance variables for ride attributes
    private String rideName;
    private String rideType;
    private int capacity;
    private boolean isOperational;
    private Employee operator; // Employee assigned to operate this ride
    
    /**
     * Default constructor - initializes with default values
     */
    public Ride() {
        this.rideName = "Unnamed Ride";
        this.rideType = "General";
        this.capacity = 10;
        this.isOperational = false;
        this.operator = null; // No operator assigned initially
        System.out.println("Ride default constructor called");
    }
    
    /**
     * Parameterized constructor - initializes with specific values
     * @param rideName Name of the ride
     * @param rideType Type/category of the ride
     * @param capacity Maximum capacity of the ride
     * @param isOperational Whether the ride is currently operational
     * @param operator Employee assigned to operate the ride
     */
    public Ride(String rideName, String rideType, int capacity, boolean isOperational, Employee operator) {
        this.rideName = rideName;
        this.rideType = rideType;
        this.capacity = capacity;
        this.isOperational = isOperational;
        this.operator = operator;
        System.out.println("Ride parameterized constructor called for: " + rideName);
    }
    
    // Getter and Setter methods for Ride attributes
    
    /**
     * Gets the ride name
     * @return The name of the ride
     */
    public String getRideName() {
        return rideName;
    }
    
    /**
     * Sets the ride name
     * @param rideName The new ride name to set
     */
    public void setRideName(String rideName) {
        this.rideName = rideName;
    }
    
    /**
     * Gets the ride type
     * @return The type of the ride
     */
    public String getRideType() {
        return rideType;
    }
    
    /**
     * Sets the ride type
     * @param rideType The new ride type to set
     */
    public void setRideType(String rideType) {
        this.rideType = rideType;
    }
    
    /**
     * Gets the ride capacity
     * @return The maximum capacity of the ride
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * Sets the ride capacity
     * @param capacity The new capacity to set
     */
    public void setCapacity(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            System.out.println("Error: Capacity must be positive");
        }
    }
    
    /**
     * Checks if the ride is operational
     * @return true if ride is operational, false otherwise
     */
    public boolean isOperational() {
        return isOperational;
    }
    
    /**
     * Sets the operational status of the ride
     * @param isOperational The new operational status
     */
    public void setOperational(boolean isOperational) {
        this.isOperational = isOperational;
    }
    
    /**
     * Gets the assigned operator
     * @return The employee operating this ride
     */
    public Employee getOperator() {
        return operator;
    }
    
    /**
     * Assigns an operator to the ride
     * @param operator The employee to assign as operator
     */
    public void setOperator(Employee operator) {
        this.operator = operator;
        if (operator != null) {
            System.out.println("Operator " + operator.getName() + " assigned to ride: " + rideName);
        } else {
            System.out.println("Operator removed from ride: " + rideName);
        }
    }
    
    /**
     * Returns a string representation of the ride
     * @return Formatted string with ride details
     */
    @Override
    public String toString() {
        String operatorInfo = (operator != null) ? operator.getName() : "No operator assigned";
        return "Ride [" + 
               "Name: " + rideName + 
               ", Type: " + rideType + 
               ", Capacity: " + capacity + 
               ", Operational: " + (isOperational ? "Yes" : "No") + 
               ", Operator: " + operatorInfo + "]";
    }
}