package School;
/**
 * This class models names of persons that are involved with a school .
 * @author stephan knappstein A01208242
 * @version 1.0
 */
public class Name {

    private final String first;
    private final String last;
    final static int MAX_CHAR = 45;




    /**
     * Construct name
     * @param first first name, to be used later for person
     * @param last last name, to be used later for person
     * @throws IllegalArgumentException if first or last name is null, blank, numeric, "admin",
     * or longer than 45 characters (maxChar).
     */
    public Name(String first, String last) {
        super();
        validateFirstName(first);
        this.first = first;
        validateLastName(last);
        this.last = last;
    }

    private static void validateFirstName(String first) {
        if (first == null || first.isBlank()) {
            throw new IllegalArgumentException("invalid first name");
        }
        if (isNumeric(first)) {
            throw new IllegalArgumentException("First Name cannot contain numeric characters");
        }
        if (max45char(first)) {
            throw new IllegalArgumentException("Name cannot be longer than 45 characters");
        }
    }

    private static void validateLastName(String last) {
        if (last == null || last.isBlank()) {
            throw new IllegalArgumentException("invalid last name");
        }
        if (isNumeric(last)) {
            throw new IllegalArgumentException("Last Name cannot contain numeric characters");
        }
        if (max45char(last)) {
            throw new IllegalArgumentException("Name cannot be longer than 45 characters");
        }
    }
    /**
     * Checks if a given string can be parsed as a number.
     * @param strNum The string to check.
     * @return true if the string is numeric, false otherwise.
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Checks if a name is "admin", ignoring case.
     * @param name The name to check.
     * @return true if the name is "admin", false otherwise.
     */
    public static boolean isAdmin (String name) {
        return name.equalsIgnoreCase("admin");
    }

    /**
     * Checks if a name is longer than 45 characters.
     * @param name The name to check.
     * @return true if the name's length is greater than 45, false otherwise.
     */
    public static boolean  max45char (String name) {
        return name.length() > MAX_CHAR;
    }


    /**
     * Gets the first name.
     * @return The first name.
     */
    public String getFirst() {
        return first;
    }


    /**
     * Gets the last name.
     * @return The last name.
     */
    public String getLast() {
        return last;
    }


    /**
     * Generates the initials from a first and last name (e.g., "S.K.").
     *
     * @return A string representing the initials.
     */
    public String getInitials() {
        String initials;
        char firstInitial = Character.toUpperCase(first.charAt(0));
        char lastInitial = Character.toUpperCase(last.charAt(0));
        initials =  firstInitial + "." + lastInitial + ".";
        return initials;
    }

    /**
     * Formats the full name with the first letter of each name capitalized.
     *
     * @return The formatted full name as a string.
     */

    public String getPrettyName() {
        String firstCharF = first.substring(0, 1).toUpperCase();
        String restofFirst =  first.substring(1).toLowerCase();
        String firstName = firstCharF + restofFirst;
        String firstCharL = last.substring(0, 1).toUpperCase();
        String restofLast =  last.substring(1).toLowerCase();
        String lastName = firstCharL + restofLast;
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Name{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                '}';
    }





}
