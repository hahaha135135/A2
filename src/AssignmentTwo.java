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
        System.out.println("=== Part 1: Classes and Inheritance Demonstration ===\n");
        
        // Create instance to call instance methods
        AssignmentTwo assignment = new AssignmentTwo();
        assignment.demoPartOne();
    }
    
    /**
     * Demonstrates Part 1 functionality - Class creation and inheritance
     * Shows how to create objects and use inheritance hierarchy
     */
    public void demoPartOne() {
        System.out.println("--- Creating Employee Objects ---");
        
        // Create employees using different constructors
        Employee defaultEmployee = new Employee();
        Employee rideOperator = new Employee("John Smith", 28, "Male", "E1001", "Ride Operations", "Operator");
        Employee supervisor = new Employee("Sarah Johnson", 35, "Female", "E1002", "Management", "Supervisor");
        
        System.out.println("\n--- Creating Visitor Objects ---");
        
        // Create visitors using different constructors
        Visitor defaultVisitor = new Visitor();
        Visitor familyVisitor = new Visitor("Mike Wilson", 32, "Male", "V2001", "Family Pass", false);
        Visitor seasonPassVisitor = new Visitor("Emma Davis", 25, "Female", "V2002", "Season Pass", true);
        
        System.out.println("\n--- Creating Ride Objects ---");
        
        // Create rides and assign operators
        Ride rollerCoaster = new Ride("Thunder Bolt", "Roller Coaster", 24, true, rideOperator);
        Ride ferrisWheel = new Ride("Sky Wheel", "Ferris Wheel", 40, true, supervisor);
        Ride waterRide = new Ride(); // Using default constructor
        waterRide.setRideName("Splash Mountain");
        waterRide.setRideType("Water Ride");
        waterRide.setCapacity(12);
        waterRide.setOperational(false); // Ride is under maintenance
        waterRide.setOperator(defaultEmployee);
        
        System.out.println("\n--- Displaying Created Objects ---");
        
        // Display employee information
        System.out.println("\nEmployees:");
        System.out.println(defaultEmployee);
        System.out.println(rideOperator);
        System.out.println(supervisor);
        
        // Display visitor information
        System.out.println("\nVisitors:");
        System.out.println(defaultVisitor);
        System.out.println(familyVisitor);
        System.out.println(seasonPassVisitor);
        
        // Display ride information
        System.out.println("\nRides:");
        System.out.println(rollerCoaster);
        System.out.println(ferrisWheel);
        System.out.println(waterRide);
        
        System.out.println("\n--- Testing Getter and Setter Methods ---");
        
        // Test getter and setter methods
        familyVisitor.setAge(33); // Update age
        familyVisitor.setTicketType("Premium Pass");
        
        rollerCoaster.setCapacity(30); // Increase capacity
        rollerCoaster.setOperational(false); // Close for maintenance
        
        System.out.println("\nUpdated Visitor: " + familyVisitor);
        System.out.println("Updated Ride: " + rollerCoaster);
        
        System.out.println("\n=== Part 1 Demonstration Completed ===");
    }
    
    // Placeholder methods for future parts (as per assignment requirements)
    public void partThree() {}
    public void partFourA() {}
    public void partFourB() {}
    public void partFive() {}
    public void partSix() {}
    public void partSeven() {}
}