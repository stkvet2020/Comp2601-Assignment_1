package School;

/**
 * This class models a person with a name and dates of birth and death.
 * @author stephan knappstein A01208242
 * @version 1.0
 */
public class Person implements Comparable<Person> {
    private final Date born;
    private Date died;
    private final Name name;

    /**
     * Constructs a Person object.
     *
     * @param born The date of birth.
     * @param name The name of the person.
     * @throws IllegalPersonException if born or name is null.
     */
    public Person(Date born, Name name) {
        if (born == null) {
            throw new IllegalPersonException("invalid date of birth");
        }
        if (name == null) {
            throw new IllegalPersonException("invalid name");
        }
        this.born = born;
        this.name = name;
        this.died = null;
    }

    /**
     * @return the date of birth.
     */
    public Date getDateOfBirth() {
        return born;
    }

    /**
     * @return the date of death, or null if alive.
     */
    public Date getDateOfDeath() {
        return died;
    }

    /**
     * @return the person's name.
     */
    public Name getName() {
        return name;
    }

    /**
     * Checks if the person is alive.
     * @return true if the person is alive, false otherwise.
     */
    public boolean isAlive() {
        return died == null;
    }

    /**
     * Sets the person's date of death.
     * @param died The date of death.
     */
    public void die(Date died) {
        this.died = died;
    }

    @Override
    public String toString() {
        if (isAlive()) {
            return String.format("%s was born %s and is still alive", name.getPrettyName(), born.getYyyyMmDd());
        } else {
            return String.format("%s was born %s and died %s", name.getPrettyName(), born.getYyyyMmDd(), died.getYyyyMmDd());
        }
    }

    @Override
    public int compareTo(Person other) {
        return this.born.compareTo(other.born);
    }
}
