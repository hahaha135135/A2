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
        System.out.println("=== Part 5: Run a Ride Cycle Demonstration ===\n");
        
        AssignmentTwo assignment = new AssignmentTwo();
        assignment.partFive();
    }
    
    /**
     * Part 5: Demonstrates running ride cycles with queue and history integration
     * Shows the complete flow from queue to history through ride cycles
     */
    public void partFive() {
        System.out.println("STARTING PART 5 DEMONSTRATION: RUN A RIDE CYCLE");
        System.out.println("==================================================\n");
        
        // Step 1: Create ride operator and ride with specific maxRider
        System.out.println("Step 1: Setting Up Ride with Operator and Max Rider Limit");
        System.out.println("-------------------------------------------------------");
        Employee operator = new Employee("Alex CycleMaster", 31, "Male", 
                                       "E5001", "Ride Operations", "Cycle Operator");
        
        // Create a roller coaster that can take 4 riders per cycle
        Ride rollerCoaster = new Ride("Thunder Cyclone", "Roller Coaster", 24, true, operator, 4);
        System.out.println("Ride created: " + rollerCoaster.getRideName());
        System.out.println("Max riders per cycle: " + rollerCoaster.getMaxRider() + "\n");
        
        // Step 2: Create 10+ visitors for the queue
        System.out.println("Step 2: Creating 10+ Visitors for Queue");
        System.out.println("--------------------------------------");
        
        Visitor[] visitors = {
            new Visitor("Visitor01-First", 20, "Male", "V5001", "Standard", false),
            new Visitor("Visitor02-Second", 25, "Female", "V5002", "Premium", true),
            new Visitor("Visitor03-Third", 18, "Male", "V5003", "Student", false),
            new Visitor("Visitor04-Fourth", 30, "Female", "V5004", "VIP", true),
            new Visitor("Visitor05-Fifth", 22, "Male", "V5005", "Standard", false),
            new Visitor("Visitor06-Sixth", 28, "Female", "V5006", "Premium", false),
            new Visitor("Visitor07-Seventh", 35, "Male", "V5007", "Standard", true),
            new Visitor("Visitor08-Eighth", 19, "Female", "V5008", "Student", false),
            new Visitor("Visitor09-Ninth", 40, "Male", "V5009", "VIP", true),
            new Visitor("Visitor10-Tenth", 26, "Female", "V5010", "Standard", false),
            new Visitor("Visitor11-Eleventh", 32, "Male", "V5011", "Premium", false), // Extra
            new Visitor("Visitor12-Twelfth", 23, "Female", "V5012", "Standard", true)  // Extra
        };
        
        System.out.println("Created " + visitors.length + " visitors for the queue.\n");
        
        // Step 3: Add all visitors to the queue
        System.out.println("Step 3: Adding All Visitors to Queue");
        System.out.println("-----------------------------------");
        for (Visitor visitor : visitors) {
            rollerCoaster.addVisitorToQueue(visitor);
        }
        System.out.println(); // Empty line for readability
        
        // Step 4: Print initial queue state
        System.out.println("Step 4: Printing Initial Queue State");
        System.out.println("-----------------------------------");
        rollerCoaster.printQueue();
        System.out.println("Initial queue size: " + rollerCoaster.getWaitingQueue().size());
        System.out.println("Initial history size: " + rollerCoaster.getRideHistory().size());
        System.out.println("Initial cycles run: " + rollerCoaster.getNumOfCycles() + "\n");
        
        // Step 5: Run one complete cycle
        System.out.println("Step 5: Running One Complete Ride Cycle");
        System.out.println("--------------------------------------");
        rollerCoaster.runOneCycle();
        
        // Step 6: Print queue after first cycle
        System.out.println("Step 6: Printing Queue After First Cycle");
        System.out.println("--------------------------------------");
        rollerCoaster.printQueue();
        System.out.println("Queue size after cycle 1: " + rollerCoaster.getWaitingQueue().size() + "\n");
        
        // Step 7: Print ride history after first cycle
        System.out.println("Step 7: Printing Ride History After First Cycle");
        System.out.println("---------------------------------------------");
        rollerCoaster.printRideHistory();
        System.out.println("History size after cycle 1: " + rollerCoaster.getRideHistory().size() + "\n");
        
        // Step 8: Run second cycle to show continued operation
        System.out.println("Step 8: Running Second Ride Cycle");
        System.out.println("-------------------------------");
        rollerCoaster.runOneCycle();
        
        // Step 9: Show state after second cycle
        System.out.println("Step 9: State After Second Cycle");
        System.out.println("-------------------------------");
        System.out.println("Queue size: " + rollerCoaster.getWaitingQueue().size());
        System.out.println("History size: " + rollerCoaster.getRideHistory().size());
        System.out.println("Total cycles run: " + rollerCoaster.getNumOfCycles() + "\n");
        
        // Step 10: Run multiple cycles to demonstrate continuous operation
        System.out.println("Step 10: Running Multiple Cycles Until Queue is Empty");
        System.out.println("---------------------------------------------------");
        
        int additionalCycles = 0;
        while (!rollerCoaster.getWaitingQueue().isEmpty()) {
            rollerCoaster.runOneCycle();
            additionalCycles++;
            
            // Safety break to prevent infinite loop
            if (additionalCycles > 10) {
                System.out.println("Safety break: Too many cycles, breaking loop");
                break;
            }
        }
        
        System.out.println("Ran " + additionalCycles + " additional cycles to clear queue\n");
        
        // Step 11: Show final state
        System.out.println("Step 11: Final State After All Cycles");
        System.out.println("-----------------------------------");
        System.out.println("Final queue size: " + rollerCoaster.getWaitingQueue().size());
        System.out.println("Final history size: " + rollerCoaster.getRideHistory().size());
        System.out.println("Final cycles run: " + rollerCoaster.getNumOfCycles());
        System.out.println("\n" + rollerCoaster.getRideStatistics() + "\n");
        
        // Step 12: Test error conditions and edge cases
        System.out.println("Step 12: Testing Error Conditions and Edge Cases");
        System.out.println("-----------------------------------------------");
        
        // Test 12a: Try to run cycle with no operator
        System.out.println("Test 12a: Running cycle with NO OPERATOR");
        Ride noOperatorRide = new Ride("Unmanned Ride", "Test", 10, true, null, 2);
        noOperatorRide.addVisitorToQueue(new Visitor("Test Visitor", 25, "Male", "VT001", "Test", false));
        noOperatorRide.runOneCycle();
        
        // Test 12b: Try to run cycle with empty queue
        System.out.println("\nTest 12b: Running cycle with EMPTY QUEUE");
        Ride emptyQueueRide = new Ride("Empty Ride", "Test", 10, true, operator, 2);
        emptyQueueRide.runOneCycle();
        
        // Test 12c: Try to run cycle with non-operational ride
        System.out.println("\nTest 12c: Running cycle with NON-OPERATIONAL RIDE");
        Ride brokenRide = new Ride("Broken Ride", "Test", 10, false, operator, 2);
        brokenRide.addVisitorToQueue(new Visitor("Test Visitor", 25, "Male", "VT002", "Test", false));
        brokenRide.runOneCycle();
        
        // Test 12d: Test with very small maxRider (1)
        System.out.println("\nTest 12d: Running cycles with MAX RIDER = 1");
        Ride singleRiderRide = new Ride("Single Rider", "Test", 5, true, operator, 1);
        for (int i = 0; i < 3; i++) {
            singleRiderRide.addVisitorToQueue(new Visitor("Single" + i, 20 + i, "Female", "VS00" + i, "Test", false));
        }
        singleRiderRide.runOneCycle();
        singleRiderRide.runOneCycle();
        System.out.println("Queue after 2 cycles: " + singleRiderRide.getWaitingQueue().size());
        
        System.out.println("\nPART 5 DEMONSTRATION COMPLETED SUCCESSFULLY!");
        System.out.println("===============================================");
        System.out.println("Summary:");
        System.out.println("- Created ride with maxRider = 4 and operator");
        System.out.println("- Added " + visitors.length + " visitors to queue");
        System.out.println("- Ran multiple ride cycles successfully");
        System.out.println("- Demonstrated queue to history transition");
        System.out.println("- Showed cycle counting and statistics");
        System.out.println("- Tested all error conditions and edge cases");
        System.out.println("- Final: " + rollerCoaster.getNumOfCycles() + " cycles run, " + 
                         rollerCoaster.getRideHistory().size() + " visitors served");
    }
    
    // Placeholder methods for future parts
    public void partSix() {}
    public void partSeven() {}
}