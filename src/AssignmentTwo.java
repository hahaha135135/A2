import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Main class for Assignment 2 - Theme Park Management System
 * Contains main method and demonstration methods for all parts
 */
public class AssignmentTwo {
    
    /**
     * Main method - entry point of the application
     * Run all parts in sequence
     */
    public static void main(String[] args) {
        AssignmentTwo assignment = new AssignmentTwo();
        // Run all parts
        assignment.runAllParts();
    }
    
    /**
     * Run all parts in sequence
     */
    public void runAllParts() {
        System.out.println("\n\nStarting Part 3: Waiting Line (Queue)...");
        partThree();
        
        System.out.println("\n\nStarting Part 4A: Ride History...");
        partFourA();
        
        System.out.println("\n\nStarting Part 4B: Sorting Ride History...");
        partFourB();
        
        System.out.println("\n\nStarting Part 5: Run a Ride Cycle...");
        partFive();
        
        System.out.println("\n\nStarting Part 6: Writing to File (Generates CSV)...");
        partSix();  // This will generate ride_history_export.csv
        
        System.out.println("\n\nStarting Part 7: Reading from File...");
        partSeven();  // This will read the file generated in Part 6
    }

    // Part 3: Waiting Line (Queue)
    public void partThree() {
        Employee operator = new Employee("Queue Manager", 32, "Male", "E3001", "Ride Operations", "Senior Operator");
        Ride rollerCoaster = new Ride("Dragon Fury", "Roller Coaster", 24, true, operator, 3);
        
        Visitor[] visitors = {
            new Visitor("Alice Adventure", 25, "Female", "V3001", "Premium", false),
            new Visitor("Bob Thrillseeker", 30, "Male", "V3002", "Standard", true),
            new Visitor("Charlie Courage", 22, "Male", "V3003", "VIP", false),
            new Visitor("Diana Daredevil", 28, "Female", "V3004", "Standard", false),
            new Visitor("Emma Excitement", 35, "Female", "V3005", "Premium", true)
        };
        
        for (Visitor visitor : visitors) {
            rollerCoaster.addVisitorToQueue(visitor);
        }
        
        System.out.println("Initial queue:");
        rollerCoaster.printQueue();
        
        rollerCoaster.removeVisitorFromQueue();
        
        System.out.println("\nQueue after removal:");
        rollerCoaster.printQueue();
    }
    
    // Part 4A: Ride History
    public void partFourA() {
        Employee operator = new Employee("History Keeper", 29, "Female", "E4001", "Ride Operations", "History Manager");
        Ride waterRide = new Ride("Splash Adventure", "Water Ride", 16, true, operator, 4);
        
        Visitor[] visitors = {
            new Visitor("Olivia Ocean", 22, "Female", "V4001", "Premium", false),
            new Visitor("Liam Lake", 45, "Male", "V4002", "Standard", true),
            new Visitor("Sophia Stream", 19, "Female", "V4003", "Student", false),
            new Visitor("Noah Navy", 33, "Male", "V4004", "VIP", true),
            new Visitor("Ava Aqua", 28, "Female", "V4005", "Standard", false)
        };
        
        for (Visitor visitor : visitors) {
            waterRide.addVisitorToHistory(visitor);
        }
        
        waterRide.checkVisitorFromHistory(visitors[0]);
        System.out.println("Number of visitors: " + waterRide.numberOfVisitors());
        
        System.out.println("\nRide History:");
        waterRide.printRideHistory();
    }
    
    // Part 4B: Sorting Ride History
    public void partFourB() {
        Employee operator = new Employee("Sorter", 34, "Male", "E4002", "Ride Operations", "Sorting Specialist");
        Ride ferrisWheel = new Ride("Sky High Wheel", "Ferris Wheel", 30, true, operator, 4);
        
        Visitor[] visitors = {
            new Visitor("Zoe Zenith", 15, "Female", "V4010", "Child", false),
            new Visitor("Adam Alpha", 45, "Male", "V4011", "Premium", true),
            new Visitor("Charlie Middle", 25, "Male", "V4012", "Standard", false),
            new Visitor("Betta Beta", 15, "Female", "V4013", "Student", true),
            new Visitor("David Delta", 32, "Male", "V4014", "VIP", false)
        };
        
        for (Visitor visitor : visitors) {
            ferrisWheel.addVisitorToHistory(visitor);
        }
        
        System.out.println("Before sorting:");
        ferrisWheel.printRideHistory();
        
        VisitorComparator comparator = new VisitorComparator();
        ferrisWheel.sortRideHistory(comparator);
        
        System.out.println("\nAfter sorting:");
        ferrisWheel.printRideHistory();
    }
    
    // Part 5: Run a Ride Cycle
    public void partFive() {
        Employee operator = new Employee("Cycle Master", 31, "Male", "E5001", "Ride Operations", "Cycle Operator");
        Ride rollerCoaster = new Ride("Thunder Cyclone", "Roller Coaster", 24, true, operator, 4);
        
        Visitor[] visitors = new Visitor[10];
        for (int i = 0; i < 10; i++) {
            visitors[i] = new Visitor("Visitor" + (i+1), 20 + i, i % 2 == 0 ? "Male" : "Female", 
                                    "V" + (500 + i), i % 3 == 0 ? "Premium" : "Standard", i % 2 == 0);
            rollerCoaster.addVisitorToQueue(visitors[i]);
        }
        
        System.out.println("Before running cycle:");
        System.out.println("Queue size: " + rollerCoaster.getWaitingQueue().size());
        System.out.println("History size: " + rollerCoaster.getRideHistory().size());
        
        rollerCoaster.runOneCycle();
        
        System.out.println("\nAfter running cycle:");
        System.out.println("Queue size: " + rollerCoaster.getWaitingQueue().size());
        System.out.println("History size: " + rollerCoaster.getRideHistory().size());
        System.out.println("Cycles run: " + rollerCoaster.getNumOfCycles());
    }
    
    // Part 6: Writing to File
    public void partSix() {
        Employee operator = new Employee("Data Exporter", 35, "Male", "E6001", "IT Department", "Data Specialist");
        Ride rollerCoaster = new Ride("Export Express", "Roller Coaster", 20, true, operator, 3);
        
        Visitor[] visitors = {
            new Visitor("John Datafield", 28, "Male", "V6001", "Premium", true),
            new Visitor("Sarah CSV", 32, "Female", "V6002", "Standard", false),
            new Visitor("Mike Fileman", 45, "Male", "V6003", "VIP", true),
            new Visitor("Emma Export", 22, "Female", "V6004", "Student", false),
            new Visitor("David Writer", 38, "Male", "V6005", "Standard", true)
        };
        
        for (Visitor visitor : visitors) {
            rollerCoaster.addVisitorToHistory(visitor);
        }
        
        String filename = "ride_history_export.csv";
        System.out.println("Exporting to file: " + filename);
        rollerCoaster.exportRideHistory(filename);
        
        // Verify file was created
        File file = new File(filename);
        if (file.exists()) {
            System.out.println("SUCCESS: File created at: " + file.getAbsolutePath());
            System.out.println("File size: " + file.length() + " bytes");
        } else {
            System.out.println("ERROR: File was not created");
        }
    }
    
    // Part 7: Reading from File
    public void partSeven() {
        // First, create a test file if it doesn't exist
        createTestImportFile();
        
        Employee operator = new Employee("Import Manager", 40, "Female", "E7001", "Data Operations", "Import Specialist");
        Ride importedRide = new Ride("Import Adventure", "Roller Coaster", 30, true, operator, 4);
        
        System.out.println("Before import:");
        System.out.println("History size: " + importedRide.numberOfVisitors());
        
        String filename = "test_ride_history.csv";
        importedRide.importRideHistory(filename);
        
        System.out.println("\nAfter import:");
        System.out.println("History size: " + importedRide.numberOfVisitors());
        
        if (importedRide.numberOfVisitors() > 0) {
            System.out.println("\nDisplaying imported visitors:");
            importedRide.printRideHistory();
        }
        
        // Test importing the file created in Part 6
        System.out.println("\n\nNow importing the file created in Part 6:");
        Ride part6Import = new Ride("Part6 Import", "Test", 20, true, operator, 3);
        part6Import.importRideHistory("ride_history_export.csv");
    }
    
    /**
     * Helper method to create a test file for import demonstration
     */
    private void createTestImportFile() {
        try {
            File testFile = new File("test_ride_history.csv");
            
            if (!testFile.exists()) {
                String content = "# Test Ride History for Import\n" +
                               "# Format: Name,Age,Gender,VisitorID,TicketType,HasSeasonPass\n" +
                               "Test Visitor 1,25,Male,TV001,Premium,true\n" +
                               "Test Visitor 2,30,Female,TV002,Standard,false\n" +
                               "Test Visitor 3,22,Male,TV003,Student,true\n" +
                               "Test Visitor 4,35,Female,TV004,VIP,false\n" +
                               "Test Visitor 5,28,Male,TV005,Standard,true\n";
                
                Files.write(testFile.toPath(), content.getBytes());
                System.out.println("Created test import file: test_ride_history.csv");
            }
        } catch (IOException e) {
            System.out.println("Failed to create test import file: " + e.getMessage());
        }
    }
}