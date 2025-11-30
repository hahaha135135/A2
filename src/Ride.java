import java.util.*;
import java.io.*;

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
    
    // Getter and Setter methods (same as before)
    public String getRideName() { return rideName; }
    public void setRideName(String rideName) { this.rideName = rideName; }
    public String getRideType() { return rideType; }
    public void setRideType(String rideType) { this.rideType = rideType; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { 
        if (capacity > 0) this.capacity = capacity; 
        else System.out.println("Error: Capacity must be positive");
    }
    public boolean isOperational() { return isOperational; }
    public void setOperational(boolean isOperational) { this.isOperational = isOperational; }
    public Employee getOperator() { return operator; }
    public void setOperator(Employee operator) { this.operator = operator; }
    public Queue<Visitor> getWaitingQueue() { return waitingQueue; }
    public LinkedList<Visitor> getRideHistory() { return rideHistory; }
    
    // Part 3: Queue Management Methods (already implemented)
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor == null) {
            System.out.println("Error: Cannot add null visitor to queue for ride: " + rideName);
            return;
        }
        boolean added = waitingQueue.offer(visitor);
        if (added) {
            System.out.println("Success: Visitor " + visitor.getName() + " added to queue for ride: " + rideName);
        } else {
            System.out.println("Error: Failed to add visitor to queue for ride: " + rideName);
        }
    }
    
    @Override
    public void removeVisitorFromQueue() {
        if (waitingQueue.isEmpty()) {
            System.out.println("Error: Cannot remove visitor from empty queue for ride: " + rideName);
            return;
        }
        Visitor removedVisitor = waitingQueue.poll();
        if (removedVisitor != null) {
            System.out.println("Success: Visitor " + removedVisitor.getName() + " removed from queue for ride: " + rideName);
        }
    }
    
    @Override
    public void printQueue() {
        System.out.println("=== Waiting Queue for Ride: " + rideName + " ===");
        if (waitingQueue.isEmpty()) {
            System.out.println("The queue is currently empty.");
        } else {
            int position = 1;
            for (Visitor visitor : waitingQueue) {
                System.out.println(position + ". " + visitor);
                position++;
            }
        }
        System.out.println("========================================");
    }
    
    // Part 4A: Ride History Management Methods - FULL IMPLEMENTATION
    
    /**
     * Adds a visitor to the ride history LinkedList
     * Called when a visitor completes the ride
     * @param visitor The visitor to add to history
     */
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("Error: Cannot add null visitor to ride history for ride: " + rideName);
            return;
        }
        
        // Add visitor to the end of the history LinkedList
        boolean added = rideHistory.add(visitor);
        
        if (added) {
            System.out.println("Success: Visitor " + visitor.getName() + 
                             " (ID: " + visitor.getVisitorId() + 
                             ") added to ride history for: " + rideName);
            System.out.println("Total visitors in history: " + rideHistory.size());
        } else {
            System.out.println("Error: Failed to add visitor " + visitor.getName() + 
                             " to ride history for: " + rideName);
        }
    }
    
    /**
     * Checks if a visitor is in the ride history using Iterator
     * Demonstrates proper use of Iterator as required
     * @param visitor The visitor to check for in history
     * @return true if visitor is found in history, false otherwise
     */
    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("Error: Cannot check null visitor in ride history for: " + rideName);
            return false;
        }
        
        if (rideHistory.isEmpty()) {
            System.out.println("Info: Ride history is empty for: " + rideName);
            return false;
        }
        
        // Use Iterator to traverse the LinkedList (REQUIREMENT)
        Iterator<Visitor> iterator = rideHistory.iterator();
        int position = 1;
        
        System.out.println("Searching for visitor in ride history...");
        while (iterator.hasNext()) {
            Visitor historyVisitor = iterator.next();
            if (historyVisitor.equals(visitor)) {
                System.out.println("Success: Visitor " + visitor.getName() + 
                                 " found in ride history at position: " + position);
                return true;
            }
            position++;
        }
        
        System.out.println("Info: Visitor " + visitor.getName() + 
                         " not found in ride history for: " + rideName);
        return false;
    }
    
    /**
     * Returns the number of visitors in the ride history
     * @return Count of visitors who have taken the ride
     */
    @Override
    public int numberOfVisitors() {
        int count = rideHistory.size();
        System.out.println("Number of visitors in ride history for " + rideName + ": " + count);
        return count;
    }
    
    /**
     * Prints all visitors in the ride history using Iterator
     * MUST use Iterator as per assignment requirements
     */
    @Override
    public void printRideHistory() {
        System.out.println("=== Ride History for: " + rideName + " ===");
        System.out.println("Ride Type: " + rideType);
        System.out.println("Total Visitors: " + rideHistory.size());
        System.out.println("----------------------------------------");
        
        if (rideHistory.isEmpty()) {
            System.out.println("No visitors have taken this ride yet.");
        } else {
            System.out.println("Visitors who have taken the ride:");
            System.out.println("-------------------------------");
            
            // USE ITERATOR as required - no marks without it!
            Iterator<Visitor> iterator = rideHistory.iterator();
            int position = 1;
            
            while (iterator.hasNext()) {
                Visitor visitor = iterator.next();
                System.out.println(position + ". " + visitor);
                position++;
            }
        }
        System.out.println("========================================");
    }
    
    // Part 4B: Sorting Implementation
    
    /**
     * Sorts the ride history using the provided Comparator
     * Uses Collections.sort() with custom Comparator
     * @param comparator The comparator to use for sorting visitors
     */
    @Override
    public void sortRideHistory(Comparator<Visitor> comparator) {
        if (rideHistory.isEmpty()) {
            System.out.println("Info: Cannot sort empty ride history for: " + rideName);
            return;
        }
        
        if (comparator == null) {
            System.out.println("Error: Cannot sort with null comparator for: " + rideName);
            return;
        }
        
        System.out.println("Sorting ride history for: " + rideName);
        System.out.println("Before sorting: " + rideHistory.size() + " visitors");
        
        // Sort the LinkedList using Collections.sort() with Comparator
        Collections.sort(rideHistory, comparator);
        
        System.out.println("Success: Ride history sorted for: " + rideName);
        System.out.println("After sorting: " + rideHistory.size() + " visitors");
    }
    
    // Additional utility methods for ride history
    
    /**
     * Gets the most recent visitor added to ride history
     * @return The most recent visitor, or null if history is empty
     */
    public Visitor getMostRecentVisitor() {
        if (rideHistory.isEmpty()) {
            return null;
        }
        return rideHistory.getLast();
    }
    
    /**
     * Gets the first visitor in ride history (chronologically first)
     * @return The first visitor, or null if history is empty
     */
    public Visitor getFirstVisitor() {
        if (rideHistory.isEmpty()) {
            return null;
        }
        return rideHistory.getFirst();
    }
    
    /**
     * Clears all visitors from ride history
     * Useful for resetting ride statistics
     */
    public void clearRideHistory() {
        int count = rideHistory.size();
        rideHistory.clear();
        System.out.println("Ride history cleared for: " + rideName + 
                         ". Removed " + count + " visitors from history.");
    }
    
    // Part 5-7 Methods - Still stubs for now
    @Override
    public void runOneCycle() {
        System.out.println("runOneCycle method called - To be implemented in Part 5");
    }
    
    @Override
    public void exportRideHistory(String filename) {
        System.out.println("exportRideHistory method called - To be implemented in Part 6");
    }
    
    @Override
    public void importRideHistory(String filename) {
        System.out.println("importRideHistory method called - To be implemented in Part 7");
    }
    
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