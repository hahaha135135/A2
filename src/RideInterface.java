import java.util.Comparator;

/**
 * Interface defining the contract for Ride operations
 * All ride classes must implement these methods
 * Using interface allows different types of rides to have common behavior
 */
public interface RideInterface {
    
    // Queue Management Methods
    
    /**
     * Adds a visitor to the waiting queue for the ride
     * @param visitor The visitor to add to the queue
     */
    void addVisitorToQueue(Visitor visitor);
    
    /**
     * Removes a visitor from the waiting queue
     * Typically removes the first visitor in line (FIFO)
     */
    void removeVisitorFromQueue();
    
    /**
     * Prints all visitors currently in the waiting queue
     * Shows the order in which visitors will be served
     */
    void printQueue();
    
    // Ride History Management Methods
    
    /**
     * Adds a visitor to the ride history
     * Called when a visitor has completed the ride
     * @param visitor The visitor to add to history
     */
    void addVisitorToHistory(Visitor visitor);
    
    /**
     * Checks if a visitor is in the ride history
     * @param visitor The visitor to check
     * @return true if visitor is in history, false otherwise
     */
    boolean checkVisitorFromHistory(Visitor visitor);
    
    /**
     * Returns the number of visitors in the ride history
     * @return Count of visitors who have taken the ride
     */
    int numberOfVisitors();
    
    /**
     * Prints all visitors in the ride history
     * Must use Iterator for traversal as per requirements
     */
    void printRideHistory();
    
    // Ride Operation Methods
    
    /**
     * Runs the ride for one cycle
     * Takes visitors from queue and adds them to history
     * Follows FIFO principle for queue management
     */
    void runOneCycle();
    
    // File Operations Methods (for Parts 6-7)
    
    /**
     * Exports ride history to a file
     * @param filename The name of the file to export to
     */
    void exportRideHistory(String filename);
    
    /**
     * Imports ride history from a file
     * @param filename The name of the file to import from
     */
    void importRideHistory(String filename);
    
    // Sorting Method (for Part 4B)
    
    /**
     * Sorts the ride history using the provided comparator
     * @param comparator The comparator to use for sorting
     */
    void sortRideHistory(Comparator<Visitor> comparator);
}