package School;

/**
 * This class models a student, who is a person with a student number.
 * @author stephan knappstein A01208242
 * @version 1.0
 */
public class Student extends Person {
    private final String studentNumber;
    private static final int STUDENT_NUMBER_LENGTH = 9;

    /**
     * Constructs a Student object.
     *
     * @param born          The date of birth.
     * @param name          The name of the student.
     * @param studentNumber The student's unique identification number.
     * @throws IllegalPersonException if born or name is null, or if the studentNumber is invalid.
     */
    public Student(Date born, Name name, String studentNumber) {
        super(born, name);
        if (studentNumber == null || studentNumber.isBlank() || studentNumber.length() != STUDENT_NUMBER_LENGTH) {
            throw new IllegalPersonException("bad student number");
        }
        this.studentNumber = studentNumber;
    }

    /**
     * @return the student's number.
     */
    public String getStudentNumber() {
        return studentNumber;
    }

    /**
     * Returns a string representation of the student.
     * The format depends on whether the student is alive or not.
     *
     * @return A formatted string describing the student.
     */
    @Override
    public String toString() {
        String studentInfo = String.format(" (student number: %s)", studentNumber);
        if (isAlive()) {
            return String.format("%s%s was born %s and is still alive",
                    getName().getPrettyName(), studentInfo, getDateOfBirth().getYyyyMmDd());
        } else {
            return String.format("%s%s was born %s and died %s",
                    getName().getPrettyName(),
                    studentInfo,
                    getDateOfBirth().getYyyyMmDd(),
                    getDateOfDeath().getYyyyMmDd());
        }
    }
}
