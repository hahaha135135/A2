import java.util.*;
import java.io.*;

/**
 * Ride class representing theme park rides/attractions
 * Implements RideInterface to provide ride functionality
 */
public class Ride implements RideInterface {
    // Instance variables
    private String rideName;
    private String rideType;
    private int capacity;
    private boolean isOperational;
    private Employee operator;
    private int maxRider;
    private int numOfCycles;
    
    // Collections
    private Queue<Visitor> waitingQueue;
    private LinkedList<Visitor> rideHistory;
    
    // Constructors
    public Ride() {
        this.rideName = "Unnamed Ride";
        this.rideType = "General";
        this.capacity = 10;
        this.isOperational = false;
        this.operator = null;
        this.maxRider = 2;
        this.numOfCycles = 0;
        this.waitingQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
    }
    
    public Ride(String rideName, String rideType, int capacity, boolean isOperational, 
                Employee operator, int maxRider) {
        this.rideName = rideName;
        this.rideType = rideType;
        this.capacity = capacity;
        this.isOperational = isOperational;
        this.operator = operator;
        this.maxRider = maxRider;
        this.numOfCycles = 0;
        this.waitingQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
    }
    
    // Getters and Setters
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
    public int getMaxRider() { return maxRider; }
    public void setMaxRider(int maxRider) { 
        if (maxRider > 0) this.maxRider = maxRider; 
        else System.out.println("Error: maxRider must be positive");
    }
    public int getNumOfCycles() { return numOfCycles; }
    public Queue<Visitor> getWaitingQueue() { return waitingQueue; }
    public LinkedList<Visitor> getRideHistory() { return rideHistory; }
    
    // Part 3: Queue Methods
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
    
    // Part 4: History Methods
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
            if (iterator.next().equals(visitor)) return true;
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
    
    // Part 5: Run Cycle Method
    @Override
    public void runOneCycle() {
        System.out.println("\nATTEMPTING TO RUN RIDE CYCLE: " + rideName);
        
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
    
    // Part 6: Export Method
    @Override
    public void exportRideHistory(String filename) {
        System.out.println("ATTEMPTING TO EXPORT RIDE HISTORY TO FILE: " + filename);
        System.out.println("Ride: " + rideName);
        System.out.println("Number of visitors to export: " + rideHistory.size());
        
        if (rideHistory.isEmpty()) {
            System.out.println("WARNING: No ride history to export. File will be empty.");
        }
        
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(filename));
            
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
            System.out.println("File location: " + new File(filename).getAbsolutePath());
            
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found or cannot be created: " + filename);
            System.out.println("Error details: " + e.getMessage());
        } catch (SecurityException e) {
            System.out.println("ERROR: Security exception - No permission to write to file: " + filename);
            System.out.println("Error details: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("ERROR: IO Exception occurred during export!");
            System.out.println("Error details: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: Unexpected error during export!");
            System.out.println("Error details: " + e.getMessage());
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
    
    private String escapeCommas(String input) {
        if (input == null) return "";
        return input.replace(",", ";");
    }
    
    // Part 7: Import Method
    @Override
    public void importRideHistory(String filename) {
        System.out.println("\nATTEMPTING TO IMPORT RIDE HISTORY FROM FILE: " + filename);
        System.out.println("Ride: " + rideName);
        System.out.println("Current history size before import: " + rideHistory.size());
        
        BufferedReader reader = null;
        int importedCount = 0;
        int skippedCount = 0;
        int lineNumber = 0;
        
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;
            
            System.out.println("Starting file import process...");
            
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                
                if (line.trim().isEmpty() || line.trim().startsWith("#")) {
                    continue;
                }
                
                try {
                    Visitor visitor = parseVisitorFromCSV(line, lineNumber);
                    if (visitor != null) {
                        boolean added = rideHistory.add(visitor);
                        if (added) importedCount++;
                        else skippedCount++;
                    } else {
                        skippedCount++;
                    }
                } catch (Exception e) {
                    skippedCount++;
                    System.out.println("ERROR: Failed to parse line " + lineNumber + ": " + e.getMessage());
                }
            }
            
            System.out.println("SUCCESS: File import completed!");
            System.out.println("Imported visitors: " + importedCount);
            System.out.println("Skipped lines: " + skippedCount);
            System.out.println("Total lines processed: " + lineNumber);
            System.out.println("Final history size: " + rideHistory.size());
            
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found: " + filename);
            System.out.println("Please check if the file exists at: " + new File(filename).getAbsolutePath());
        } catch (SecurityException e) {
            System.out.println("ERROR: Security exception - No permission to read file: " + filename);
            System.out.println("Error details: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("ERROR: IO Exception occurred during import!");
            System.out.println("Error details: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: Unexpected error during import!");
            System.out.println("Error type: " + e.getClass().getName());
            System.out.println("Error message: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                    System.out.println("File reader closed successfully.");
                } catch (IOException e) {
                    System.out.println("WARNING: Error closing file reader: " + e.getMessage());
                }
            }
        }
        
        System.out.println("Import process completed.\n");
    }
    
    private Visitor parseVisitorFromCSV(String csvLine, int lineNumber) {
        try {
            String[] parts = csvLine.split(",");
            
            if (parts.length < 6) {
                System.out.println("WARNING: Line " + lineNumber + 
                                 " has insufficient data. Expected 6 fields, found " + parts.length);
                return null;
            }
            
            String name = unescapeCommas(parts[0].trim());
            int age;
            try {
                age = Integer.parseInt(parts[1].trim());
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid age format on line " + lineNumber + ": " + parts[1]);
                return null;
            }
            
            String gender = unescapeCommas(parts[2].trim());
            String visitorId = unescapeCommas(parts[3].trim());
            String ticketType = unescapeCommas(parts[4].trim());
            boolean hasSeasonPass;
            
            try {
                hasSeasonPass = Boolean.parseBoolean(parts[5].trim());
            } catch (Exception e) {
                System.out.println("WARNING: Invalid boolean format on line " + lineNumber + ", defaulting to false");
                hasSeasonPass = false;
            }
            
            if (name.isEmpty()) {
                name = "Unknown Visitor";
            }
            
            if (visitorId.isEmpty()) {
                visitorId = "IMP" + lineNumber;
            }
            
            if (age < 0 || age > 150) {
                age = 25;
            }
            
            return new Visitor(name, age, gender, visitorId, ticketType, hasSeasonPass);
            
        } catch (Exception e) {
            System.out.println("ERROR: Failed to parse CSV line " + lineNumber);
            return null;
        }
    }
    
    private String unescapeCommas(String input) {
        if (input == null) return "";
        return input.replace(";", ",");
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