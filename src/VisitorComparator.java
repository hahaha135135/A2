import java.util.Comparator;

/**
 * Custom Comparator for sorting Visitor objects
 * Implements Comparator interface to define custom sorting logic
 * MUST use Comparator (not Comparable) as per assignment requirements
 */
public class VisitorComparator implements Comparator<Visitor> {
    
    /**
     * Compares two Visitor objects for sorting
     * Uses multiple criteria as required by assignment:
     * 1. Primary: Age (youngest to oldest)
     * 2. Secondary: Name (alphabetical order)
     * @param v1 the first visitor to compare
     * @param v2 the second visitor to compare
     * @return negative if v1 < v2, positive if v1 > v2, zero if equal
     */
    @Override
    public int compare(Visitor v1, Visitor v2) {
        // Primary comparison: Age (ascending - youngest first)
        int ageComparison = Integer.compare(v1.getAge(), v2.getAge());
        
        if (ageComparison != 0) {
            return ageComparison; // Different ages, return age comparison
        }
        
        // Secondary comparison: Name (alphabetical order)
        // Using case-insensitive comparison for consistency
        return v1.getName().compareToIgnoreCase(v2.getName());
    }
    
    /**
     * Alternative comparator that sorts by ticket type and then by age
     * Demonstrates flexibility of Comparator pattern
     */
    public static class ByTicketTypeAndAge implements Comparator<Visitor> {
        @Override
        public int compare(Visitor v1, Visitor v2) {
            // Primary: Ticket type (alphabetical)
            int ticketComparison = v1.getTicketType().compareToIgnoreCase(v2.getTicketType());
            
            if (ticketComparison != 0) {
                return ticketComparison;
            }
            
            // Secondary: Age (descending - oldest first)
            return Integer.compare(v2.getAge(), v1.getAge());
        }
    }
    
    /**
     * Alternative comparator that sorts by season pass status and name
     * Season pass holders come first, then alphabetical by name
     */
    public static class BySeasonPassAndName implements Comparator<Visitor> {
        @Override
        public int compare(Visitor v1, Visitor v2) {
            // Primary: Season pass holders first
            boolean v1HasPass = v1.hasSeasonPass();
            boolean v2HasPass = v2.hasSeasonPass();
            
            if (v1HasPass && !v2HasPass) {
                return -1; // v1 comes first (has pass, v2 doesn't)
            } else if (!v1HasPass && v2HasPass) {
                return 1;  // v2 comes first (has pass, v1 doesn't)
            } else {
                // Both have same pass status, sort by name
                return v1.getName().compareToIgnoreCase(v2.getName());
            }
        }
    }
}