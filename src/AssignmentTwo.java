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
        System.out.println("=== Part 2: Abstract Class and Interface Demonstration ===\n");
        
        // Create instance to call instance methods
        AssignmentTwo assignment = new AssignmentTwo();
        assignment.demoPartTwo();
    }
    
    /**
     * Demonstrates Part 2 functionality - Abstract class and interface
     * Shows abstraction and interface implementation
     */
    public void demoPartTwo() {
        System.out.println("--- Testing Abstract Class (Person) ---");
        
        // Person is now abstract - cannot instantiate directly
        // This would cause compilation error:
        // Person person = new Person(); // INVALID - Person is abstract
        
        System.out.println("Person class is now abstract - cannot create Person objects directly");
        System.out.println("Must create objects through concrete subclasses (Employee, Visitor)\n");
        
        System.out.println("--- Testing Concrete Subclasses ---");
        
        // Create employees and visitors
        Employee operator = new Employee("John Smith", 28, "Male", "E1001", "Ride Operations", "Operator");
        Visitor visitor = new Visitor("Alice Brown", 25, "Female", "V2001", "Premium", true);
        
        // Test the abstract method implementation
        System.out.println("\nEmployee Role: " + operator.getRole());
        System.out.println("Visitor Role: " + visitor.getRole());
        
        System.out.println("\n--- Testing Interface Implementation ---");
        
        // Create a ride that implements RideInterface
        Ride rollerCoaster = new Ride("Thunder Bolt", "Roller Coaster", 24, true, operator);
        
        System.out.println("\nRide implements RideInterface: " + (rollerCoaster instanceof RideInterface));
        System.out.println("Ride object: " + rollerCoaster);
        
        System.out.println("\n--- Testing Interface Methods (Stubs) ---");
        
        // Test interface methods (currently stubs)
        Visitor testVisitor = new Visitor("Test Visitor", 30, "Male", "V9999", "General", false);
        
        System.out.println("\nCalling interface methods (stub implementations):");
        rollerCoaster.addVisitorToQueue(testVisitor);
        rollerCoaster.removeVisitorFromQueue();
        rollerCoaster.printQueue();
        rollerCoaster.addVisitorToHistory(testVisitor);
        rollerCoaster.checkVisitorFromHistory(testVisitor);
        rollerCoaster.numberOfVisitors();
        rollerCoaster.printRideHistory();
        rollerCoaster.runOneCycle();
        
        System.out.println("\n--- Interface Benefits Demonstration ---");
        
        // Demonstrate polymorphism with interface
        RideInterface rideInterface = rollerCoaster; // Can reference implementation through interface
        
        System.out.println("\nUsing Ride interface reference:");
        rideInterface.addVisitorToQueue(testVisitor);
        
        // Create multiple rides that implement the same interface
        Ride ferrisWheel = new Ride("Sky Wheel", "Ferris Wheel", 40, true, operator);
        Ride waterRide = new Ride("Splash Mountain", "Water Ride", 12, false, operator);
        
        // Store different rides in an array using the interface type
        RideInterface[] parkRides = {rollerCoaster, ferrisWheel, waterRide};
        
        System.out.println("\n--- Processing All Rides Using Interface ---");
        for (RideInterface ride : parkRides) {
            System.out.println("Processing ride through interface...");
            ride.addVisitorToQueue(testVisitor); // Same method call, different implementations
        }
        
        System.out.println("\n=== Part 2 Demonstration Completed ===");
        System.out.println("Key concepts demonstrated:");
        System.out.println("1. Abstract class (Person) cannot be instantiated");
        System.out.println("2. Concrete classes (Employee, Visitor) implement abstract methods");
        System.out.println("3. Interface (RideInterface) defines contract for ride operations");
        System.out.println("4. Polymorphism through interface references");
        System.out.println("5. Stub methods ready for implementation in subsequent parts");
    }
    
    // Placeholder methods for future parts
    public void partThree() {}
    public void partFourA() {}
    public void partFourB() {}
    public void partFive() {}
    public void partSix() {}
    public void partSeven() {}
}