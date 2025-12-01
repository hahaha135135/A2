import java.io.File;
import java.util.Date;

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
        System.out.println("=== Part 6: Writing to a File Demonstration ===\n");
        
        AssignmentTwo assignment = new AssignmentTwo();
        assignment.partSix();
    }
    
    /**
     * Part 6: Demonstrates writing ride history to a CSV file
     * Shows file I/O operations with proper exception handling
     */
    public void partSix() {
        System.out.println("STARTING PART 6 DEMONSTRATION: WRITING TO FILE");
        System.out.println("=============================================\n");
        
        // Step 1: Create ride operator and ride
        System.out.println("Step 1: Setting Up Ride for Export Demonstration");
        System.out.println("----------------------------------------------");
        Employee operator = new Employee("Data Exporter", 35, "Male", 
                                       "E6001", "IT Department", "Data Specialist");
        
        Ride rollerCoaster = new Ride("Export Express", "Roller Coaster", 20, true, operator, 3);
        System.out.println("Ride created: " + rollerCoaster.getRideName() + "\n");
        
        // Step 2: Create 5+ visitors and add them to ride history
        System.out.println("Step 2: Creating Visitors for Ride History");
        System.out.println("-----------------------------------------");
        
        Visitor[] visitors = {
            new Visitor("John Datafield", 28, "Male", "V6001", "Premium", true),
            new Visitor("Sarah CSV", 32, "Female", "V6002", "Standard", false),
            new Visitor("Mike Fileman", 45, "Male", "V6003", "VIP", true),
            new Visitor("Emma Export", 22, "Female", "V6004", "Student", false),
            new Visitor("David Writer", 38, "Male", "V6005", "Standard", true),
            new Visitor("Lisa Document", 29, "Female", "V6006", "Premium", false), // Extra
            new Visitor("Tom Backup", 51, "Male", "V6007", "VIP", true)           // Extra
        };
        
        System.out.println("Created " + visitors.length + " visitors for ride history.\n");
        
        // Step 3: Add all visitors to ride history
        System.out.println("Step 3: Adding Visitors to Ride History");
        System.out.println("--------------------------------------");
        for (Visitor visitor : visitors) {
            rollerCoaster.addVisitorToHistory(visitor);
        }
        System.out.println(); // Empty line
        
        // Step 4: Display current ride history before export
        System.out.println("Step 4: Displaying Current Ride History");
        System.out.println("--------------------------------------");
        rollerCoaster.printRideHistory();
        System.out.println(); // Empty line
        
        // Step 5: Validate data before export
        System.out.println("Step 5: Validating Export Data");
        System.out.println("-----------------------------");
        boolean isValid = rollerCoaster.validateExportData();
        System.out.println("Data validation result: " + (isValid ? "PASSED" : "FAILED") + "\n");
        
        // Step 6: Export ride history to CSV file (main demonstration)
        System.out.println("Step 6: Exporting Ride History to CSV File");
        System.out.println("-----------------------------------------");
        
        String filename = "ride_history_export.csv";
        System.out.println("Target file: " + filename);
        System.out.println("Export started at: " + new Date());
        
        rollerCoaster.exportRideHistory(filename);
        
        // Step 7: Verify the exported file
        System.out.println("Step 7: Verifying Exported File");
        System.out.println("------------------------------");
        verifyExportedFile(filename);
        
        // Step 8: Test export with different scenarios
        System.out.println("Step 8: Testing Different Export Scenarios");
        System.out.println("-----------------------------------------");
        
        // Test 8a: Export empty ride history
        System.out.println("\nTest 8a: Exporting Empty Ride History");
        Ride emptyRide = new Ride("Empty Ride", "Test", 10, true, operator, 2);
        emptyRide.exportRideHistory("empty_history.csv");
        
        // Test 8b: Export with custom options
        System.out.println("\nTest 8b: Exporting with Custom Format");
        rollerCoaster.exportRideHistory("custom_export.csv", true, "|");
        
        // Test 8c: Test error conditions
        System.out.println("\nTest 8c: Testing Error Conditions");
        
        // Try to export to invalid filename
        System.out.println("\nTesting invalid filename:");
        rollerCoaster.exportRideHistory("/invalid/path/file.csv");
        
        // Try to export with visitor containing commas in name (testing escapeCommas)
        System.out.println("\nTesting export with special characters:");
        Ride specialRide = new Ride("Special Ride", "Test", 10, true, operator, 2);
        Visitor specialVisitor = new Visitor("Test, Comma, Name", 25, "Male", "V9999", "Test,Type", false);
        specialRide.addVisitorToHistory(specialVisitor);
        specialRide.exportRideHistory("special_chars.csv");
        
        // Step 9: Show file content preview
        System.out.println("Step 9: Previewing Exported File Content");
        System.out.println("---------------------------------------");
        previewFileContent(filename);
        
        System.out.println("\nPART 6 DEMONSTRATION COMPLETED SUCCESSFULLY!");
        System.out.println("===========================================");
        System.out.println("Summary:");
        System.out.println("- Created ride with " + visitors.length + " visitors in history");
        System.out.println("- Implemented exportRideHistory() method with CSV format");
        System.out.println("- Added proper exception handling for file I/O operations");
        System.out.println("- Demonstrated data validation before export");
        System.out.println("- Tested various export scenarios and error conditions");
        System.out.println("- Files created: ride_history_export.csv, empty_history.csv, custom_export.csv, special_chars.csv");
    }
    
    /**
     * Helper method to verify that a file was created successfully
     * @param filename The name of the file to verify
     */
    private void verifyExportedFile(String filename) {
        File file = new File(filename);
        
        if (file.exists()) {
            System.out.println("SUCCESS: File created: " + filename);
            System.out.println("File size: " + file.length() + " bytes");
            System.out.println("Absolute path: " + file.getAbsolutePath());
            System.out.println("Last modified: " + new Date(file.lastModified()));
            
            // Check if file is readable and writable
            System.out.println("Readable: " + file.canRead());
            System.out.println("Writable: " + file.canWrite());
        } else {
            System.out.println("ERROR: File was not created: " + filename);
        }
    }
    
    /**
     * Helper method to preview the first few lines of a file
     * @param filename The name of the file to preview
     */
    private void previewFileContent(String filename) {
        System.out.println("Preview of file: " + filename);
        System.out.println("First 5 lines:");
        System.out.println("---------------");
        
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filename))) {
            String line;
            int lineCount = 0;
            
            while ((line = reader.readLine()) != null && lineCount < 5) {
                System.out.println(line);
                lineCount++;
            }
            
            if (lineCount == 0) {
                System.out.println("(File is empty)");
            }
            
        } catch (java.io.FileNotFoundException e) {
            System.out.println("ERROR: File not found: " + filename);
        } catch (java.io.IOException e) {
            System.out.println("ERROR: Cannot read file: " + e.getMessage());
        }
    }
    
    // Placeholder method for future part
    public void partSeven() {}
}