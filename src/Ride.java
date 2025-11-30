import java.util.*;

/**
 * Ride class representing theme park rides/attractions
 * Implements RideInterface to provide ride functionality
 * Manages ride information, waiting queue, and ride history
 */
public class Ride implements RideInterface {
    // Instance variables for ride attributes
    private String rideName;
    private String rideType;
    private int capacity;
    private boolean isOperational;
    private Employee operator; // Employee assigned to operate this ride
    
    // Collections for queue and history management
    private Queue<Visitor> waitingQueue;     // For Part 3 - Waiting line (FIFO)
    private LinkedList<Visitor> rideHistory; // For Part 4 - Ride history
    
    /**
     * Default constructor - initializes with default values
     * Also initializes the collections
     */
    public Ride() {
        this.rideName = "Unnamed Ride";
        this.rideType = "General";
        this.capacity = 10;
        this.isOperational = false;
        this.operator = null;
        
        // Initialize collections
        this.waitingQueue = new LinkedList<>();     // LinkedList implements Queue interface
        this.rideHistory = new LinkedList<>();
        
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
        
        // Initialize collections
        this.waitingQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        
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
     * Gets the waiting queue (for testing purposes)
     * @return The waiting queue of visitors
     */
    public Queue<Visitor> getWaitingQueue() {
        return waitingQueue;
    }
    
    /**
     * Gets the ride history (for testing purposes)
     * @return The ride history linked list
     */
    public LinkedList<Visitor> getRideHistory() {
        return rideHistory;
    }
    
    // Part 3: Queue Management Methods - FULL IMPLEMENTATION
    
    /**
     * Adds a visitor to the waiting queue for the ride
     * Implements FIFO (First-In-First-Out) principle
     * @param visitor The visitor to add to the queue
     */
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor == null) {
            System.out.println("Error: Cannot add null visitor to queue for ride: " + rideName);
            return;
        }
        
        // Add visitor to the end of the queue
        boolean added = waitingQueue.offer(visitor);
        
        if (added) {
            System.out.println("Success: Visitor " + visitor.getName() + 
                             " (ID: " + visitor.getVisitorId() + 
                             ") added to queue for ride: " + rideName);
            System.out.println("Current queue position: " + waitingQueue.size());
        } else {
            System.out.println("Error: Failed to add visitor " + visitor.getName() + 
                             " to queue for ride: " + rideName);
        }
    }
    
    /**
     * Removes a visitor from the waiting queue
     * Removes the first visitor in line (FIFO principle)
     * Typically called when a visitor takes the ride or leaves the queue
     */
    @Override
    public void removeVisitorFromQueue() {
        if (waitingQueue.isEmpty()) {
            System.out.println("Error: Cannot remove visitor from empty queue for ride: " + rideName);
            return;
        }
        
        // Remove and get the first visitor in queue
        Visitor removedVisitor = waitingQueue.poll();
        
        if (removedVisitor != null) {
            System.out.println("Success: Visitor " + removedVisitor.getName() + 
                             " (ID: " + removedVisitor.getVisitorId() + 
                             ") removed from queue for ride: " + rideName);
            System.out.println("Remaining visitors in queue: " + waitingQueue.size());
        } else {
            System.out.println("Error: Unexpected error while removing visitor from queue for ride: " + rideName);
        }
    }
    
    /**
     * Prints all visitors currently in the waiting queue
     * Shows the order in which visitors will be served (FIFO)
     * Displays detailed information about each visitor
     */
    @Override
    public void printQueue() {
        System.out.println("=== Waiting Queue for Ride: " + rideName + " ===");
        System.out.println("Ride Type: " + rideType);
        System.out.println("Operational: " + (isOperational ? "Yes" : "No"));
        System.out.println("Operator: " + (operator != null ? operator.getName() : "No operator assigned"));
        System.out.println("Total Visitors in Queue: " + waitingQueue.size());
        System.out.println("----------------------------------------");
        
        if (waitingQueue.isEmpty()) {
            System.out.println("The queue is currently empty.");
            System.out.println("No visitors waiting for this ride.");
        } else {
            System.out.println("Queue Order (First to Last):");
            System.out.println("----------------------------");
            
            int position = 1;
            // Iterate through the queue without removing elements
            for (Visitor visitor : waitingQueue) {
                System.out.println(position + ". " + visitor);
                position++;
            }
            
            // Show who is next in line
            Visitor nextInLine = waitingQueue.peek();
            if (nextInLine != null) {
                System.out.println("----------------------------------------");
                System.out.println("Next in line: " + nextInLine.getName() + 
                                 " (ID: " + nextInLine.getVisitorId() + ")");
            }
        }
        System.out.println("========================================");
    }
    
    // Additional queue utility methods (not in interface but useful)
    
    /**
     * Gets the number of visitors in the waiting queue
     * @return The size of the waiting queue
     */
    public int getQueueSize() {
        return waitingQueue.size();
    }
    
    /**
     * Checks if the waiting queue is empty
     * @return true if queue is empty, false otherwise
     */
    public boolean isQueueEmpty() {
        return waitingQueue.isEmpty();
    }
    
    /**
     * Gets the next visitor in line without removing them
     * @return The next visitor in queue, or null if queue is empty
     */
    public Visitor peekNextVisitor() {
        return waitingQueue.peek();
    }
    
    /**
     * Clears all visitors from the waiting queue
     * Useful for ride maintenance or emergency situations
     */
    public void clearQueue() {
        int clearedCount = waitingQueue.size();
        waitingQueue.clear();
        System.out.println("Queue cleared for ride: " + rideName + 
                         ". Removed " + clearedCount + " visitors from queue.");
    }
    
    // Part 4-7 Methods - Still stubs for now
    
    /**
     * Adds a visitor to the ride history
     * @param visitor The visitor to add to history
     */
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        // Full implementation in Part 4A
        System.out.println("addVisitorToHistory method called - To be implemented in Part 4A");
    }
    
    /**
     * Checks if a visitor is in the ride history
     * @param visitor The visitor to check
     * @return true if visitor is in history, false otherwise
     */
    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        // Full implementation in Part 4A
        System.out.println("checkVisitorFromHistory method called - To be implemented in Part 4A");
        return false;
    }
    
    /**
     * Returns the number of visitors in the ride history
     * @return Count of visitors who have taken the ride
     */
    @Override
    public int numberOfVisitors() {
        // Full implementation in Part 4A
        System.out.println("numberOfVisitors method called - To be implemented in Part 4A");
        return 0;
    }
    
    /**
     * Prints all visitors in the ride history
     */
    @Override
    public void printRideHistory() {
        // Full implementation in Part 4A
        System.out.println("printRideHistory method called - To be implemented in Part 4A");
    }
    
    /**
     * Runs the ride for one cycle
     */
    @Override
    public void runOneCycle() {
        // Full implementation in Part 5
        System.out.println("runOneCycle method called - To be implemented in Part 5");
    }
    
    /**
     * Exports ride history to a file
     * @param filename The name of the file to export to
     */
    @Override
    public void exportRideHistory(String filename) {
        // Full implementation in Part 6
        System.out.println("exportRideHistory method called - To be implemented in Part 6");
    }
    
    /**
     * Imports ride history from a file
     * @param filename The name of the file to import from
     */
    @Override
    public void importRideHistory(String filename) {
        // Full implementation in Part 7
        System.out.println("importRideHistory method called - To be implemented in Part 7");
    }
    
    /**
     * Sorts the ride history using the provided comparator
     * @param comparator The comparator to use for sorting
     */
    @Override
    public void sortRideHistory(Comparator<Visitor> comparator) {
        // Full implementation in Part 4B
        System.out.println("sortRideHistory method called - To be implemented in Part 4B");
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
               ", Operator: " + operatorInfo + 
               ", Queue Size: " + waitingQueue.size() + 
               ", History Size: " + rideHistory.size() + "]";
    }
}