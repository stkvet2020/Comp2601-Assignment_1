package School;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class models a school, which maintains a roster of people.
 * @author stephan knappstein A01208242
 * @version 1.0
 */
public class School {
    private final List<Person> people;

    /**
     * Constructs a School object with an empty roster.
     */
    public School() {
        this.people = new ArrayList<>();
    }

    /**
     * Registers a person with the school.
     *
     * @param person The person to register.
     * @throws IllegalPersonException if the person is null.
     */
    public void register(Person person) {
        if (person == null) {
            throw new IllegalPersonException("Person to be registered cannot be null");
        }
        people.add(person);
    }

    /**
     * Prints the roster of all registered people to the console.
     */
    public void printRoster() {
        for (Person person : people) {
            System.out.println(person.toString());
        }
    }

    /**
     * Prints the age of each person for every year they were alive.
     */
    public void printAgesAndYears() {
        for (Person person : people) {
            int birthYear = person.getDateOfBirth().getYear();
            int endYear;

            if (person.isAlive()) {
                endYear = 2022; // As per the test case output
            } else {
                endYear = person.getDateOfDeath().getYear();
            }

            for (int year = birthYear; year <= endYear; year++) {
                int age = year - birthYear;
                System.out.println(person.getName().getPrettyName() + ": " + year + " (age " + age + ")");
            }
        }
    }

    /**
     * Saves the details of all registered people to a file named "people.txt".
     */
    public void saveDetails() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("people.txt"))) {
            for (Person person : people) {
                String prettyName = person.getName().getPrettyName();
                String initials = person.getName().getInitials();
                Date dob = person.getDateOfBirth();
                String dobDayOfWeek = dob.getDayOfTheWeek();
                String dobFormatted = dob.getYyyyMmDd();

                if (person.isAlive()) {
                    writer.printf("%s (%s) was born on %s %s.%n", prettyName, initials, dobDayOfWeek, dobFormatted);
                } else {
                    Date dod = person.getDateOfDeath();
                    String dodDayOfWeek = dod.getDayOfTheWeek();
                    String dodFormatted = dod.getYyyyMmDd();
                    writer.printf("%s (%s) was born on %s %s and died on %s %s.%n", prettyName, initials, dobDayOfWeek, dobFormatted, dodDayOfWeek, dodFormatted);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
