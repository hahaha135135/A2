/**
 * Employee class representing theme park staff
 * Extends Person class to inherit common attributes
 */
public class Employee extends Person {
    // Additional instance variables specific to employees
    private String employeeId;
    private String department;
    private String position;
    
    /**
     * Default constructor - initializes with default values
     */
    public Employee() {
        super(); // Calls parent class default constructor
        this.employeeId = "E0000";
        this.department = "Unassigned";
        this.position = "Staff";
        System.out.println("Employee default constructor called");
    }
    
    /**
     * Parameterized constructor - initializes with specific values
     * @param name Employee's name (inherited from Person)
     * @param age Employee's age (inherited from Person)
     * @param gender Employee's gender (inherited from Person)
     * @param employeeId Unique identifier for the employee
     * @param department Department where employee works
     * @param position Job position of the employee
     */
    public Employee(String name, int age, String gender, String employeeId, String department, String position) {
        super(name, age, gender); // Calls parent class parameterized constructor
        this.employeeId = employeeId;
        this.department = department;
        this.position = position;
        System.out.println("Employee parameterized constructor called for: " + name);
    }
    
    // Getter and Setter methods for Employee-specific attributes
    
    /**
     * Gets the employee ID
     * @return The employee's unique ID
     */
    public String getEmployeeId() {
        return employeeId;
    }
    
    /**
     * Sets the employee ID
     * @param employeeId The new employee ID to set
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
    /**
     * Gets the department where employee works
     * @return The employee's department
     */
    public String getDepartment() {
        return department;
    }
    
    /**
     * Sets the employee's department
     * @param department The new department to assign
     */
    public void setDepartment(String department) {
        this.department = department;
    }
    
    /**
     * Gets the employee's job position
     * @return The employee's position
     */
    public String getPosition() {
        return position;
    }
    
    /**
     * Sets the employee's job position
     * @param position The new position to assign
     */
    public void setPosition(String position) {
        this.position = position;
    }
    
    /**
     * Returns a string representation of the employee
     * Includes both inherited and employee-specific attributes
     * @return Formatted string with employee details
     */
    @Override
    public String toString() {
        return "Employee [" + 
               "Name: " + getName() + 
               ", Age: " + getAge() + 
               ", Gender: " + getGender() + 
               ", ID: " + employeeId + 
               ", Department: " + department + 
               ", Position: " + position + "]";
    }
}