import java.util.*;

/**
 * Main class for Assignment 2 - Theme Park Management System
 * Contains main method and demonstration methods for all parts
 */
public class AssignmentTwo {
    
    /**
     * Main method - entry point of the application
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== PROG2004 Assignment 2 - Theme Park Management System ===");
        System.out.println("=== Part 3: Waiting Line (Queue) Demonstration ===\n");
        
        // Create instance to call instance methods
        AssignmentTwo assignment = new AssignmentTwo();
        assignment.partThree();
    }
    
    /**
     * Demonstrates Part 3 functionality - Queue implementation for waiting line
     * Shows FIFO queue operations for ride waiting system
     */
    public void partThree() {
        System.out.println("🚀 STARTING PART 3 DEMONSTRATION: WAITING LINE QUEUE");
        System.out.println("====================================================\n");
        
        // Step 1: Create ride operator
        System.out.println("Step 1: Creating Ride Operator");
        System.out.println("-----------------------------");
        Employee rideOperator = new Employee("John RideMaster", 32, "Male", 
                                           "E3001", "Ride Operations", "Senior Operator");
        System.out.println("Operator created: " + rideOperator.getName() + "\n");
        
        // Step 2: Create a roller coaster ride
        System.out.println("Step 2: Creating Roller Coaster Ride");
        System.out.println("-----------------------------------");
        Ride rollerCoaster = new Ride("Dragon Fury", "Roller Coaster", 24, true, rideOperator);
        System.out.println("Ride created: " + rollerCoaster.getRideName() + "\n");
        
        // Step 3: Create 5+ visitors for the queue
        System.out.println("Step 3: Creating Visitors for Queue");
        System.out.println("----------------------------------");
        
        Visitor[] visitors = {
            new Visitor("Alice Adventure", 25, "Female", "V3001", "Premium", false),
            new Visitor("Bob Thrillseeker", 30, "Male", "V3002", "Standard", true),
            new Visitor("Charlie Courage", 22, "Male", "V3003", "VIP", false),
            new Visitor("Diana Daredevil", 28, "Female", "V3004", "Standard", false),
            new Visitor("Emma Excitement", 35, "Female", "V3005", "Premium", true),
            new Visitor("Frank Fearless", 19, "Male", "V3006", "Student", false) // Extra visitor
        };
        
        System.out.println("Created " + visitors.length + " visitors for the queue.\n");
        
        // Step 4: Add all visitors to the queue
        System.out.println("Step 4: Adding Visitors to Queue");
        System.out.println("-------------------------------");
        for (Visitor visitor : visitors) {
            rollerCoaster.addVisitorToQueue(visitor);
        }
        System.out.println(); // Empty line for readability
        
        // Step 5: Print the initial queue
        System.out.println("Step 5: Printing Initial Queue");
        System.out.println("-----------------------------");
        rollerCoaster.printQueue();
        System.out.println(); // Empty line for readability
        
        // Step 6: Remove one visitor from the queue
        System.out.println("Step 6: Removing One Visitor from Queue");
        System.out.println("--------------------------------------");
        rollerCoaster.removeVisitorFromQueue();
        System.out.println(); // Empty line for readability
        
        // Step 7: Print the queue after removal
        System.out.println("Step 7: Printing Queue After Removal");
        System.out.println("-----------------------------------");
        rollerCoaster.printQueue();
        System.out.println(); // Empty line for readability
        
        // Step 8: Demonstrate additional queue operations
        System.out.println("Step 8: Additional Queue Operations");
        System.out.println("----------------------------------");
        
        // Check queue size
        System.out.println("Current queue size: " + rollerCoaster.getQueueSize());
        
        // Check if queue is empty
        System.out.println("Is queue empty? " + rollerCoaster.isQueueEmpty());
        
        // Peek at next visitor
        Visitor nextVisitor = rollerCoaster.peekNextVisitor();
        if (nextVisitor != null) {
            System.out.println("Next visitor in line: " + nextVisitor.getName());
        }
        System.out.println(); // Empty line for readability
        
        // Step 9: Test edge cases
        System.out.println("Step 9: Testing Edge Cases");
        System.out.println("--------------------------");
        
        // Test adding null visitor
        System.out.println("Testing null visitor addition:");
        rollerCoaster.addVisitorToQueue(null);
        
        // Test removing from empty queue (create temporary empty ride)
        System.out.println("\nTesting removal from empty queue:");
        Ride emptyRide = new Ride("Test Ride", "Test", 10, true, rideOperator);
        emptyRide.removeVisitorFromQueue();
        
        System.out.println(); // Empty line for readability
        
        // Step 10: Final queue status
        System.out.println("Step 10: Final Queue Status");
        System.out.println("---------------------------");
        rollerCoaster.printQueue();
        
        System.out.println("\n🎉 PART 3 DEMONSTRATION COMPLETED SUCCESSFULLY!");
        System.out.println("===============================================");
        System.out.println("Summary:");
        System.out.println("- Created " + visitors.length + " visitors");
        System.out.println("- Added all visitors to queue (FIFO)");
        System.out.println("- Removed 1 visitor from front of queue");
        System.out.println("- Demonstrated queue printing and utility methods");
        System.out.println("- Tested error handling and edge cases");
        System.out.println("- Final queue size: " + rollerCoaster.getQueueSize());
    }
    
    // Placeholder methods for future parts
    public void partFourA() {}
    public void partFourB() {}
    public void partFive() {}
    public void partSix() {}
    public void partSeven() {}
}