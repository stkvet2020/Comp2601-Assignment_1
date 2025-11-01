package School;

/**
 * This class models a teacher, who is a person with a specialty.
 * @author stephan knappstein A01208242
 * @version 1.0
 */
public class Teacher extends Person {
    private final String specialty;

    /**
     * Constructs a Teacher object.
     *
     * @param born      The date of birth.
     * @param name      The name of the teacher.
     * @param specialty The teacher's area of expertise.
     * @throws IllegalPersonException if born or name is null, or if the specialty is invalid.
     */
    public Teacher(Date born, Name name, String specialty) {
        super(born, name);
        if (specialty == null || specialty.isBlank()) {
            throw new IllegalPersonException("bad specialty");
        }
        this.specialty = specialty;
    }

    private static void validateSpecialty(String specialty, Date born) {
        try {
        if (specialty == null || specialty.isBlank()) {
                throw new IllegalPersonException("Specialty cannot be blank or null");
            }else if (born==null){
                throw new IllegalPersonException("Bad date of birth");
            }
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    }// end of validateSpecialty

    /**
     * @return the teacher's specialty.
     */
    public String getSpecialty() {
        return specialty;
    }

    /**
     * Returns a string representation of the teacher.
     * The format depends on whether the teacher is alive or not.
     *
     * @return A formatted string describing the teacher.
     */
    @Override
    public String toString() {
        String teacherInfo = String.format(" (specialty: %s)", specialty);
        if (isAlive()) {
            return String.format("%s%s was born %s and is still alive",
                    getName().getPrettyName(), teacherInfo, getDateOfBirth().getYyyyMmDd());
        } else {
            return String.format("%s%s was born %s and died %s",
                    getName().getPrettyName(),
                    teacherInfo,
                    getDateOfBirth().getYyyyMmDd(),
                    getDateOfDeath().getYyyyMmDd());
        }
    }

    
}
