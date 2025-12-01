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
    public int getMaxRider() { return maxRider; }
    public void setMaxRider(int maxRider) { 
        if (maxRider > 0) this.maxRider = maxRider; 
        else System.out.println("Error: maxRider must be positive");
    }
    public int getNumOfCycles() { return numOfCycles; }
    
    // Part 3-5 Methods (implemented in previous parts)
    
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
    
    @Override
    public void runOneCycle() {
        System.out.println("\nATTEMPTING TO RUN RIDE CYCLE: " + rideName);
        System.out.println("==========================================");
        
        if (operator == null) {
            System.out.println("ERROR: Cannot run ride - No operator assigned to: " + rideName);
            return;
        }
        
        if (waitingQueue.isEmpty()) {
            System.out.println("ERROR: Cannot run ride - No visitors in queue for: " + rideName);
            return;
        }
        
        if (!isOperational) {
            System.out.println("ERROR: Cannot run ride - " + rideName + " is not operational");
            return;
        }
        
        int visitorsToTake = Math.min(maxRider, waitingQueue.size());
        System.out.println("STARTING RIDE CYCLE - Taking " + visitorsToTake + " visitors");
        
        for (int i = 0; i < visitorsToTake; i++) {
            Visitor rider = waitingQueue.poll();
            if (rider != null) {
                rideHistory.add(rider);
                System.out.println("SUCCESS: " + rider.getName() + " has taken the ride!");
            }
        }
        
        numOfCycles++;
        System.out.println("RIDE CYCLE COMPLETED SUCCESSFULLY!");
        System.out.println("Cycle Number: " + numOfCycles);
        System.out.println("Remaining in queue: " + waitingQueue.size());
        System.out.println("Total in ride history: " + rideHistory.size());
        System.out.println("==========================================\n");
    }
    
    // Part 6: Writing to File - FULL IMPLEMENTATION
    
    /**
     * Exports ride history to a CSV file
     * Each visitor's details are written on a separate line in comma-separated format
     * Implements proper exception handling as required
     * 
     * @param filename The name of the file to write to
     */
    @Override
public void exportRideHistory(String filename) {
    System.out.println("ATTEMPTING TO EXPORT RIDE HISTORY TO FILE: " + filename);
    System.out.println("Ride: " + rideName);
    System.out.println("Number of visitors to export: " + rideHistory.size());
    
    PrintWriter writer = null;
    try {
        writer = new PrintWriter(new FileWriter(filename));
        
        // Write header lines
        writer.println("# Ride History Export for: " + rideName);
        writer.println("# Export Date: " + new Date());
        writer.println("# Format: Name,Age,Gender,VisitorID,TicketType,HasSeasonPass");
        
        int exportCount = 0;
        for (Visitor visitor : rideHistory) {
            String line = String.format("%s,%d,%s,%s,%s,%b",
                escapeCommas(visitor.getName()),
                visitor.getAge(),
                escapeCommas(visitor.getGender()),
                escapeCommas(visitor.getVisitorId()),
                escapeCommas(visitor.getTicketType()),
                visitor.hasSeasonPass());
            
            writer.println(line);
            exportCount++;
        }
        
        writer.flush();
        System.out.println("SUCCESS: Exported " + exportCount + " visitors to file: " + filename);
        
    } catch (FileNotFoundException e) {
        System.out.println("ERROR: File not found or cannot be created: " + filename);
        System.out.println("Error details: " + e.getMessage());
        
    } catch (SecurityException e) {
        System.out.println("ERROR: Security exception - No permission to write to file: " + filename);
        System.out.println("Error details: " + e.getMessage());
        
    } catch (IOException e) {
        System.out.println("ERROR: IO Exception occurred during export!");
        System.out.println("Error type: " + e.getClass().getName());
        System.out.println("Error message: " + e.getMessage());
        
    } catch (Exception e) {
        System.out.println("ERROR: Unexpected error during export!");
        System.out.println("Error type: " + e.getClass().getName());
        System.out.println("Error message: " + e.getMessage());
        
    } finally {
        if (writer != null) {
            try {
                writer.close();
                System.out.println("File writer closed successfully.");
            } catch (Exception e) {
                System.out.println("WARNING: Error closing file writer: " + e.getMessage());
            }
        }
    }
    
    System.out.println("Export process completed.\n");
}
    
    /**
     * Helper method to escape commas in string values
     * Replaces commas with a placeholder to avoid CSV parsing issues
     * 
     * @param input The input string that may contain commas
     * @return The string with commas replaced by semicolons
     */
    private String escapeCommas(String input) {
        if (input == null) {
            return "";
        }
        // Replace commas with semicolons to maintain CSV format
        return input.replace(",", ";");
    }
    
    /**
     * Alternative export method with additional options
     * Allows specifying whether to include header and formatting options
     * 
     * @param filename The name of the file to write to
     * @param includeHeader Whether to include header information
     * @param delimiter The delimiter to use (default is comma)
     */
    public void exportRideHistory(String filename, boolean includeHeader, String delimiter) {
        System.out.println("Exporting ride history with custom options...");
        
        if (delimiter == null || delimiter.trim().isEmpty()) {
            delimiter = ",";
        }
        
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(filename));
            
            if (includeHeader) {
                writer.println("# Custom export for ride: " + rideName);
                writer.println("# Total visitors: " + rideHistory.size());
                writer.println("# Delimiter: " + delimiter);
            }
            
            int count = 0;
            for (Visitor visitor : rideHistory) {
                // Use specified delimiter
                String line = String.format("%s" + delimiter + "%d" + delimiter + "%s" + delimiter + "%s" + delimiter + "%s" + delimiter + "%b",
                    visitor.getName(), visitor.getAge(), visitor.getGender(),
                    visitor.getVisitorId(), visitor.getTicketType(), visitor.hasSeasonPass());
                
                writer.println(line);
                count++;
            }
            
            System.out.println("SUCCESS: Exported " + count + " visitors using custom format.");
            
        } catch (IOException e) {
            System.out.println("ERROR: Custom export failed: " + e.getMessage());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
    
    /**
     * Validates the export data before writing to file
     * Checks for potential issues like null values or formatting problems
     * 
     * @return true if data is valid for export, false otherwise
     */
    public boolean validateExportData() {
        System.out.println("Validating export data for ride: " + rideName);
        
        if (rideHistory.isEmpty()) {
            System.out.println("WARNING: No data to export.");
            return true; // Empty data is still valid
        }
        
        int validCount = 0;
        int invalidCount = 0;
        
        for (Visitor visitor : rideHistory) {
            if (visitor == null) {
                System.out.println("ERROR: Found null visitor in history!");
                invalidCount++;
                continue;
            }
            
            // Check for required fields
            if (visitor.getName() == null || visitor.getName().trim().isEmpty()) {
                System.out.println("WARNING: Visitor has empty name");
            }
            
            if (visitor.getVisitorId() == null || visitor.getVisitorId().trim().isEmpty()) {
                System.out.println("WARNING: Visitor has empty ID");
            }
            
            if (visitor.getAge() < 0 || visitor.getAge() > 150) {
                System.out.println("WARNING: Visitor has invalid age: " + visitor.getAge());
            }
            
            validCount++;
        }
        
        System.out.println("Validation complete:");
        System.out.println("  Valid records: " + validCount);
        System.out.println("  Invalid records: " + invalidCount);
        
        return invalidCount == 0;
    }
    
    // Part 7 Method - Still stub for now
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