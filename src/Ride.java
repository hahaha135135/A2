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
    
    // Part 5: Additional instance variables for ride operation
    private int maxRider;      // Maximum riders per cycle
    private int numOfCycles;   // Number of times ride has been run
    
    // Collections for queue and history management
    private Queue<Visitor> waitingQueue;     // For Part 3 - Waiting line (FIFO)
    private LinkedList<Visitor> rideHistory; // For Part 4 - Ride history
    
    /**
     * Default constructor - initializes with default values
     * Also initializes the collections and ride operation variables
     */
    public Ride() {
        this.rideName = "Unnamed Ride";
        this.rideType = "General";
        this.capacity = 10;
        this.isOperational = false;
        this.operator = null;
        this.maxRider = 2;     // Default max riders per cycle
        this.numOfCycles = 0;  // Start with 0 cycles
        
        // Initialize collections
        this.waitingQueue = new LinkedList<>();
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
     * @param maxRider Maximum number of riders per cycle
     */
    public Ride(String rideName, String rideType, int capacity, boolean isOperational, 
                Employee operator, int maxRider) {
        this.rideName = rideName;
        this.rideType = rideType;
        this.capacity = capacity;
        this.isOperational = isOperational;
        this.operator = operator;
        this.maxRider = maxRider;
        this.numOfCycles = 0;  // Start with 0 cycles
        
        // Initialize collections
        this.waitingQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        
        System.out.println("Ride parameterized constructor called for: " + rideName);
    }
    
    // Getter and Setter methods
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
    
    // Part 5: Getter and Setter for ride operation variables
    public int getMaxRider() { return maxRider; }
    public void setMaxRider(int maxRider) { 
        if (maxRider > 0) this.maxRider = maxRider; 
        else System.out.println("Error: maxRider must be positive");
    }
    public int getNumOfCycles() { return numOfCycles; }
    // Note: No setter for numOfCycles - it should only be incremented by runOneCycle()
    
    // Part 3: Queue Management Methods
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
    
    // Part 4: Ride History Management Methods
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("Error: Cannot add null visitor to ride history for ride: " + rideName);
            return;
        }
        boolean added = rideHistory.add(visitor);
        if (added) {
            System.out.println("Success: Visitor " + visitor.getName() + " added to ride history for: " + rideName);
        }
    }
    
    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("Error: Cannot check null visitor in ride history for: " + rideName);
            return false;
        }
        Iterator<Visitor> iterator = rideHistory.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(visitor)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int numberOfVisitors() {
        return rideHistory.size();
    }
    
    @Override
    public void printRideHistory() {
        System.out.println("=== Ride History for: " + rideName + " ===");
        if (rideHistory.isEmpty()) {
            System.out.println("No visitors have taken this ride yet.");
        } else {
            Iterator<Visitor> iterator = rideHistory.iterator();
            int position = 1;
            while (iterator.hasNext()) {
                System.out.println(position + ". " + iterator.next());
                position++;
            }
        }
        System.out.println("========================================");
    }
    
    @Override
    public void sortRideHistory(Comparator<Visitor> comparator) {
        if (rideHistory.isEmpty()) {
            System.out.println("Info: Cannot sort empty ride history for: " + rideName);
            return;
        }
        Collections.sort(rideHistory, comparator);
        System.out.println("Success: Ride history sorted for: " + rideName);
    }
    
    // Part 5: Run One Cycle Method - FULL IMPLEMENTATION
    
    /**
     * Runs the ride for one cycle according to assignment requirements:
     * 1. Checks if operator is assigned
     * 2. Checks if there are waiting visitors
     * 3. Takes visitors from queue (up to maxRider)
     * 4. Adds them to ride history
     * 5. Updates cycle count
     */
    @Override
    public void runOneCycle() {
        System.out.println("\nATTEMPTING TO RUN RIDE CYCLE: " + rideName);
        System.out.println("==========================================");
        
        // Check 1: Verify operator is assigned
        if (operator == null) {
            System.out.println("ERROR: Cannot run ride - No operator assigned to: " + rideName);
            System.out.println("Please assign an operator before running the ride.");
            return;
        }
        
        // Check 2: Verify there are waiting visitors
        if (waitingQueue.isEmpty()) {
            System.out.println("ERROR: Cannot run ride - No visitors in queue for: " + rideName);
            System.out.println("Visitors must be in the queue to run the ride.");
            return;
        }
        
        // Check 3: Verify ride is operational
        if (!isOperational) {
            System.out.println("ERROR: Cannot run ride - " + rideName + " is not operational");
            System.out.println("The ride must be marked as operational to run.");
            return;
        }
        
        // All checks passed - proceed with ride cycle
        System.out.println("SUCCESS: All pre-flight checks passed!");
        System.out.println("Operator: " + operator.getName());
        System.out.println("Visitors in queue: " + waitingQueue.size());
        System.out.println("Max riders per cycle: " + maxRider);
        
        // Calculate how many visitors to take in this cycle
        int visitorsToTake = Math.min(maxRider, waitingQueue.size());
        System.out.println("\nSTARTING RIDE CYCLE - Taking " + visitorsToTake + " visitors");
        System.out.println("----------------------------------------");
        
        List<Visitor> ridersThisCycle = new ArrayList<>();
        
        // Process visitors for this cycle
        for (int i = 0; i < visitorsToTake; i++) {
            Visitor rider = waitingQueue.poll(); // Remove from front of queue
            if (rider != null) {
                // Add to ride history
                rideHistory.add(rider);
                ridersThisCycle.add(rider);
                
                System.out.println("SUCCESS: " + rider.getName() + 
                                 " (ID: " + rider.getVisitorId() + 
                                 ") has taken the ride!");
            }
        }
        
        // Update cycle count
        numOfCycles++;
        
        // Print cycle summary
        System.out.println("----------------------------------------");
        System.out.println("RIDE CYCLE COMPLETED SUCCESSFULLY!");
        System.out.println("Ride: " + rideName);
        System.out.println("Cycle Number: " + numOfCycles);
        System.out.println("Visitors served this cycle: " + ridersThisCycle.size());
        System.out.println("Remaining in queue: " + waitingQueue.size());
        System.out.println("Total in ride history: " + rideHistory.size());
        
        // Show who rode in this cycle
        if (!ridersThisCycle.isEmpty()) {
            System.out.println("\nVisitors who rode this cycle:");
            for (int i = 0; i < ridersThisCycle.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + ridersThisCycle.get(i).getName());
            }
        }
        
        System.out.println("==========================================\n");
    }
    
    // Additional utility methods for ride operation
    
    /**
     * Gets detailed ride operation statistics
     * @return String with ride operation details
     */
    public String getRideStatistics() {
        return String.format(
            "Ride Statistics for %s:\n" +
            "  - Total Cycles Run: %d\n" +
            "  - Max Riders per Cycle: %d\n" +
            "  - Current Queue Size: %d\n" +
            "  - Total Visitors Served: %d\n" +
            "  - Operator: %s\n" +
            "  - Operational Status: %s",
            rideName, numOfCycles, maxRider, waitingQueue.size(), 
            rideHistory.size(), 
            (operator != null ? operator.getName() : "No operator"),
            (isOperational ? "Operational" : "Maintenance")
        );
    }
    
    /**
     * Simulates running multiple cycles at once
     * @param cycles Number of cycles to run
     */
    public void runMultipleCycles(int cycles) {
        if (cycles <= 0) {
            System.out.println("Error: Number of cycles must be positive");
            return;
        }
        
        System.out.println("ATTEMPTING TO RUN " + cycles + " CYCLES FOR: " + rideName);
        int successfulCycles = 0;
        
        for (int i = 1; i <= cycles; i++) {
            System.out.println("\n--- Cycle " + i + " of " + cycles + " ---");
            
            // Store current state to check if cycle actually ran
            int queueSizeBefore = waitingQueue.size();
            runOneCycle();
            int queueSizeAfter = waitingQueue.size();
            
            // Check if cycle actually processed visitors
            if (queueSizeBefore > queueSizeAfter) {
                successfulCycles++;
            } else {
                System.out.println("Cycle " + i + " did not process any visitors - stopping");
                break;
            }
        }
        
        System.out.println("\nCompleted " + successfulCycles + " out of " + cycles + " requested cycles");
    }
    
    /**
     * Resets ride operation statistics (cycles count)
     * Useful for daily reset or testing
     */
    public void resetRideStatistics() {
        System.out.println("Resetting ride statistics for: " + rideName);
        int oldCycleCount = numOfCycles;
        numOfCycles = 0;
        System.out.println("Reset cycle count from " + oldCycleCount + " to 0");
    }
    
    // Part 6-7 Methods - Still stubs for now
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
               ", Max Riders/Cycle: " + maxRider +
               ", Cycles Run: " + numOfCycles +
               ", Queue Size: " + waitingQueue.size() + 
               ", History Size: " + rideHistory.size() + "]";
    }
}