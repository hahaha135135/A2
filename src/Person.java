/**
 * Abstract base class representing a general person
 * This class will never be instantiated directly
 */
public abstract class Person {
    // Instance variables for person attributes
    private String name;
    private int age;
    private String gender;
    
    /**
     * Default constructor - initializes with default values
     */
    public Person() {
        this.name = "Unknown";
        this.age = 0;
        this.gender = "Unknown";
        System.out.println("Person default constructor called");
    }
    
    /**
     * Parameterized constructor - initializes with specific values
     * @param name The name of the person
     * @param age The age of the person
     * @param gender The gender of the person
     */
    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        System.out.println("Person parameterized constructor called for: " + name);
    }
    
    // Getter and Setter methods with documentation
    
    /**
     * Gets the name of the person
     * @return The person's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the name of the person
     * @param name The new name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the age of the person
     * @return The person's age
     */
    public int getAge() {
        return age;
    }
    
    /**
     * Sets the age of the person
     * @param age The new age to set
     */
    public void setAge(int age) {
        // Basic validation for age
        if (age >= 0 && age <= 150) {
            this.age = age;
        } else {
            System.out.println("Error: Invalid age value");
        }
    }
    
    /**
     * Gets the gender of the person
     * @return The person's gender
     */
    public String getGender() {
        return gender;
    }
    
    /**
     * Sets the gender of the person
     * @param gender The new gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    /**
     * Returns a string representation of the person
     * @return Formatted string with person details
     */
    @Override
    public String toString() {
        return "Person [Name: " + name + ", Age: " + age + ", Gender: " + gender + "]";
    }
}