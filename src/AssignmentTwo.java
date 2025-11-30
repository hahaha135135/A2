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
        System.out.println("=== Part 4: Ride History and Sorting Demonstration ===\n");
        
        AssignmentTwo assignment = new AssignmentTwo();
        
        // Run both Part 4A and Part 4B demonstrations
        assignment.partFourA();
        System.out.println("\n" + "=".repeat(60) + "\n");
        assignment.partFourB();
    }
    
    /**
     * Part 4A: Demonstrates ride history functionality with LinkedList
     * Shows adding visitors to history, checking existence, and printing with Iterator
     */
    public void partFourA() {
        System.out.println("🚀 STARTING PART 4A DEMONSTRATION: RIDE HISTORY");
        System.out.println("===============================================\n");
        
        // Step 1: Create ride operator and ride
        System.out.println("Step 1: Setting Up Ride and Operator");
        System.out.println("-----------------------------------");
        Employee operator = new Employee("Sarah HistoryKeeper", 29, "Female", 
                                       "E4001", "Ride Operations", "History Manager");
        Ride waterRide = new Ride("Splash Adventure", "Water Ride", 16, true, operator);
        System.out.println("Ride created: " + waterRide.getRideName() + "\n");
        
        // Step 2: Create 5+ visitors for ride history
        System.out.println("Step 2: Creating Visitors for Ride History");
        System.out.println("-----------------------------------------");
        
        Visitor[] visitors = {
            new Visitor("Olivia Ocean", 22, "Female", "V4001", "Premium", false),
            new Visitor("Liam Lake", 45, "Male", "V4002", "Standard", true),
            new Visitor("Sophia Stream", 19, "Female", "V4003", "Student", false),
            new Visitor("Noah Navy", 33, "Male", "V4004", "VIP", true),
            new Visitor("Ava Aqua", 28, "Female", "V4005", "Standard", false),
            new Visitor("William Wave", 15, "Male", "V4006", "Child", true)  // Extra visitor
        };
        
        System.out.println("Created " + visitors.length + " visitors for ride history.\n");
        
        // Step 3: Add all visitors to ride history
        System.out.println("Step 3: Adding Visitors to Ride History");
        System.out.println("--------------------------------------");
        for (Visitor visitor : visitors) {
            waterRide.addVisitorToHistory(visitor);
        }
        System.out.println(); // Empty line for readability
        
        // Step 4: Check if specific visitors are in history
        System.out.println("Step 4: Checking Visitors in History");
        System.out.println("-----------------------------------");
        waterRide.checkVisitorFromHistory(visitors[0]); // Should be found
        waterRide.checkVisitorFromHistory(visitors[2]); // Should be found
        
        // Check for a visitor not in history
        Visitor unknownVisitor = new Visitor("Unknown Person", 99, "Unknown", "V9999", "Test", false);
        waterRide.checkVisitorFromHistory(unknownVisitor); // Should not be found
        System.out.println(); // Empty line for readability
        
        // Step 5: Get number of visitors in history
        System.out.println("Step 5: Counting Visitors in History");
        System.out.println("-----------------------------------");
        int count = waterRide.numberOfVisitors();
        System.out.println("Verified count: " + count + " visitors\n");
        
        // Step 6: Print ride history using Iterator (REQUIRED)
        System.out.println("Step 6: Printing Ride History (Using Iterator)");
        System.out.println("---------------------------------------------");
        waterRide.printRideHistory();
        System.out.println(); // Empty line for readability
        
        // Step 7: Test edge cases
        System.out.println("Step 7: Testing Edge Cases");
        System.out.println("--------------------------");
        
        // Test adding null visitor
        System.out.println("Testing null visitor addition:");
        waterRide.addVisitorToHistory(null);
        
        // Test checking null visitor
        System.out.println("\nTesting null visitor check:");
        waterRide.checkVisitorFromHistory(null);
        
        // Test with empty history (create new ride)
        System.out.println("\nTesting with empty history:");
        Ride emptyRide = new Ride("Empty Ride", "Test", 10, true, operator);
        emptyRide.checkVisitorFromHistory(visitors[0]);
        emptyRide.printRideHistory();
        
        System.out.println("\n🎉 PART 4A DEMONSTRATION COMPLETED SUCCESSFULLY!");
        System.out.println("===============================================");
    }
    
    /**
     * Part 4B: Demonstrates sorting ride history using Comparator
     * Shows sorting functionality with custom comparison logic
     */
    public void partFourB() {
        System.out.println("🚀 STARTING PART 4B DEMONSTRATION: SORTING RIDE HISTORY");
        System.out.println("======================================================\n");
        
        // Step 1: Create ride operator and ride
        System.out.println("Step 1: Setting Up Ride for Sorting Demonstration");
        System.out.println("------------------------------------------------");
        Employee operator = new Employee("Mike Sorter", 34, "Male", 
                                       "E4002", "Ride Operations", "Sorting Specialist");
        Ride ferrisWheel = new Ride("Sky High Wheel", "Ferris Wheel", 30, true, operator);
        System.out.println("Ride created: " + ferrisWheel.getRideName() + "\n");
        
        // Step 2: Create visitors with varied attributes for sorting
        System.out.println("Step 2: Creating Visitors with Varied Attributes");
        System.out.println("-----------------------------------------------");
        
        Visitor[] visitors = {
            new Visitor("Zoe Zenith", 15, "Female", "V4010", "Child", false),
            new Visitor("Adam Alpha", 45, "Male", "V4011", "Premium", true),
            new Visitor("Charlie Middle", 25, "Male", "V4012", "Standard", false),
            new Visitor("Betta Beta", 15, "Female", "V4013", "Student", true), // Same age as Zoe
            new Visitor("David Delta", 32, "Male", "V4014", "VIP", false),
            new Visitor("Eve Epsilon", 25, "Female", "V4015", "Standard", true) // Same age as Charlie
        };
        
        System.out.println("Created " + visitors.length + " visitors with varied ages and ticket types.\n");
        
        // Step 3: Add visitors to ride history (unsorted order)
        System.out.println("Step 3: Adding Visitors to History (Unsorted Order)");
        System.out.println("--------------------------------------------------");
        // Add in specific order to demonstrate sorting
        ferrisWheel.addVisitorToHistory(visitors[1]); // Adam, 45
        ferrisWheel.addVisitorToHistory(visitors[0]); // Zoe, 15
        ferrisWheel.addVisitorToHistory(visitors[3]); // Betta, 15 (same age as Zoe)
        ferrisWheel.addVisitorToHistory(visitors[2]); // Charlie, 25
        ferrisWheel.addVisitorToHistory(visitors[5]); // Eve, 25 (same age as Charlie)
        ferrisWheel.addVisitorToHistory(visitors[4]); // David, 32
        System.out.println(); // Empty line for readability
        
        // Step 4: Print unsorted history
        System.out.println("Step 4: Printing UNSORTED Ride History");
        System.out.println("-------------------------------------");
        ferrisWheel.printRideHistory();
        System.out.println(); // Empty line for readability
        
        // Step 5: Create comparator and sort the history
        System.out.println("Step 5: Sorting Ride History with Comparator");
        System.out.println("-------------------------------------------");
        VisitorComparator comparator = new VisitorComparator();
        System.out.println("Using comparator: Age (ascending) -> Name (alphabetical)");
        ferrisWheel.sortRideHistory(comparator);
        System.out.println(); // Empty line for readability
        
        // Step 6: Print sorted history
        System.out.println("Step 6: Printing SORTED Ride History");
        System.out.println("-----------------------------------");
        ferrisWheel.printRideHistory();
        System.out.println(); // Empty line for readability
        
        // Step 7: Demonstrate alternative comparators
        System.out.println("Step 7: Demonstrating Alternative Comparators");
        System.out.println("--------------------------------------------");
        
        // Create a new ride for alternative sorting demonstration
        Ride testRide = new Ride("Test Ride", "Demo", 20, true, operator);
        
        // Add same visitors but in different order
        for (Visitor visitor : visitors) {
            testRide.addVisitorToHistory(visitor);
        }
        
        System.out.println("\nOriginal order:");
        testRide.printRideHistory();
        
        // Sort by ticket type and age
        System.out.println("\nSorting by Ticket Type and Age:");
        VisitorComparator.ByTicketTypeAndAge ticketComparator = new VisitorComparator.ByTicketTypeAndAge();
        testRide.sortRideHistory(ticketComparator);
        testRide.printRideHistory();
        
        // Sort by season pass and name
        System.out.println("\nSorting by Season Pass and Name:");
        VisitorComparator.BySeasonPassAndName passComparator = new VisitorComparator.BySeasonPassAndName();
        testRide.sortRideHistory(passComparator);
        testRide.printRideHistory();
        
        // Step 8: Test edge cases for sorting
        System.out.println("Step 8: Testing Sorting Edge Cases");
        System.out.println("----------------------------------");
        
        // Test sorting empty history
        System.out.println("Testing sorting empty history:");
        Ride emptyRide = new Ride("Empty Ride", "Test", 10, true, operator);
        emptyRide.sortRideHistory(comparator);
        
        // Test sorting with null comparator
        System.out.println("\nTesting sorting with null comparator:");
        try {
            ferrisWheel.sortRideHistory(null);
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
        
        System.out.println("\n🎉 PART 4B DEMONSTRATION COMPLETED SUCCESSFULLY!");
        System.out.println("===============================================");
        System.out.println("Summary:");
        System.out.println("- Created custom Comparator for Visitor objects");
        System.out.println("- Implemented multi-criteria sorting (age + name)");
        System.out.println("- Demonstrated Collections.sort() with Comparator");
        System.out.println("- Showed before/after sorting results");
        System.out.println("- Tested alternative sorting strategies");
        System.out.println("- Handled edge cases appropriately");
    }
    
    // Placeholder methods for future parts
    public void partFive() {}
    public void partSix() {}
    public void partSeven() {}
}