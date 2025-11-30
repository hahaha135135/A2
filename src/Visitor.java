/**
 * Visitor class representing theme park visitors
 * Extends Person class to inherit common attributes
 * Implements abstract method from Person class
 */
public class Visitor extends Person {
    // Additional instance variables specific to visitors
    private String visitorId;
    private String ticketType;
    private boolean hasSeasonPass;
    
    /**
     * Default constructor - initializes with default values
     */
    public Visitor() {
        super(); // Calls parent class default constructor
        this.visitorId = "V0000";
        this.ticketType = "General";
        this.hasSeasonPass = false;
        System.out.println("Visitor default constructor called");
    }
    
    /**
     * Parameterized constructor - initializes with specific values
     * @param name Visitor's name (inherited from Person)
     * @param age Visitor's age (inherited from Person)
     * @param gender Visitor's gender (inherited from Person)
     * @param visitorId Unique identifier for the visitor
     * @param ticketType Type of ticket purchased
     * @param hasSeasonPass Whether visitor has a season pass
     */
    public Visitor(String name, int age, String gender, String visitorId, String ticketType, boolean hasSeasonPass) {
        super(name, age, gender); // Calls parent class parameterized constructor
        this.visitorId = visitorId;
        this.ticketType = ticketType;
        this.hasSeasonPass = hasSeasonPass;
        System.out.println("Visitor parameterized constructor called for: " + name);
    }
    
    // Getter and Setter methods for Visitor-specific attributes
    
    /**
     * Gets the visitor ID
     * @return The visitor's unique ID
     */
    public String getVisitorId() {
        return visitorId;
    }
    
    /**
     * Sets the visitor ID
     * @param visitorId The new visitor ID to set
     */
    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }
    
    /**
     * Gets the type of ticket
     * @return The visitor's ticket type
     */
    public String getTicketType() {
        return ticketType;
    }
    
    /**
     * Sets the ticket type
     * @param ticketType The new ticket type to set
     */
    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }
    
    /**
     * Checks if visitor has a season pass
     * @return true if visitor has season pass, false otherwise
     */
    public boolean hasSeasonPass() {
        return hasSeasonPass;
    }
    
    /**
     * Sets the season pass status
     * @param hasSeasonPass The new season pass status
     */
    public void setHasSeasonPass(boolean hasSeasonPass) {
        this.hasSeasonPass = hasSeasonPass;
    }
    
    /**
     * Implements abstract method from Person class
     * Defines the specific role of a Visitor
     * @return A string describing the visitor's role
     */
    @Override
    public String getRole() {
        String role = "Theme Park Visitor";
        if (hasSeasonPass) {
            role += " (Season Pass Holder)";
        } else {
            role += " (" + ticketType + " Ticket)";
        }
        return role;
    }
    
    /**
     * Returns a string representation of the visitor
     * Includes both inherited and visitor-specific attributes
     * @return Formatted string with visitor details
     */
    @Override
    public String toString() {
        return "Visitor [" + 
               "Name: " + getName() + 
               ", Age: " + getAge() + 
               ", Gender: " + getGender() + 
               ", ID: " + visitorId + 
               ", Ticket: " + ticketType + 
               ", Season Pass: " + (hasSeasonPass ? "Yes" : "No") + "]";
    }
}