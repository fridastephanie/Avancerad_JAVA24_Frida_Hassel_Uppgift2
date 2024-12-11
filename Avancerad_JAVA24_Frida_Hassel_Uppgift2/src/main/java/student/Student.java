package student;

public class Student {
    //Skapar privat referensvariabel för students ID
    private int studentId;
    //Skapar privat referensvariabel för students namn
    private String studentName;
    //Skapar privat referensvariabel för students betyg
    private char studentGrade;

    /**
     * Konstruktor som inhämtar och sätter värden för studentens ID, namn och betyg.
     * @param studentId
     * @param studentName
     * @param studentGrade
     */
    public Student(int studentId, String studentName, char studentGrade) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentGrade = studentGrade;
    }

    /**
     * Getter för students ID.
     * @return studentId
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Getter för students namn.
     * @return studentName
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Getter för students betyg.
     * @return studentGrade
     */
    public char getStudentGrade() {
        return studentGrade;
    }

    /**
     * ToString när objektet ska skrivas ut.
     * @return
     */
    @Override
    public String toString() {
        return "Student-ID: " + studentId + ", Namn: " + studentName + ", Betyg: " + studentGrade;
    }
}

