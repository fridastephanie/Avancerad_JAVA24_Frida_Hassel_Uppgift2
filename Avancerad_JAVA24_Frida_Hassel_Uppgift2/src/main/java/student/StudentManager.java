package student;
import logger.FileLogger;
import java.util.HashMap;
import java.util.Map;

public class StudentManager {
    //Privat statisk instansvariabel för att implementera Singleton-mönstret
    private static StudentManager instance;
    //Skapar privat HashMap med Integer som nyckeltyp och Student som värde/innehåll
    private Map<Integer, Student> students = new HashMap<>();
    //Skapar ett objekt av klassen FileLogger
    private FileLogger fileLogger = new FileLogger();

    /**
     * Skapar en tom privat konstruktor för att ingen annan klass ska kunna skapa ett objekt av klassen, för att implementera Singleton-mönstret.
     */
    private StudentManager() {

    }

    /**
     * Public statics metod som används för att andra klasser ska kunna hämta den enda instans av StudentManager.
     * Om instance är null, skapas ett nytt objekt av StudentManager, annars returneras den existerande instansen.
     * @return
     */
    public static StudentManager getInstance() {
        if (instance == null) {
            instance = new StudentManager();
        }
        return instance;
    }

    /**
     * Metod som anropar en annan metod, som lägger till innehållet från textfilen i listan (HashMap).
     */
    public void loadStudentsFromFile() {
        fileLogger.loadStudentsFromFile(students);
    }

    /**
     * Metod som inhämtar tre egenskaper och skapar ett Student objekt, som läggs till i listan (HashMap).
     * @param studentId
     * @param studentName
     * @param studentGrade
     */
    public void addStudent(int studentId, String studentName, char studentGrade) {
        students.put(studentId, new Student(studentId, studentName, studentGrade));
    }

    /**
     * Metod som inhämtar ett student-ID och därefter letar efter en nyckel som matchar, vid träff hämtas och returneras värdet/innehållet till nyckeln.
     * @param studentId
     * @return
     */
    public Student searchStudentById(int studentId) {
        return students.get(studentId);
    }

    /**
     * Metod som antingen meddelar användaren att listan (HashMap) är tom, eller skriver ut allt innehåll från listan (HashMap).
     */
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("Listan är tom");
        } else {
            for (Student student : students.values()) {
                System.out.println(student);
            }
        }
    }

    /**
     * Metod som anropar en annan metod, som lägger till innehållet från listan (HashMap) i textfilen.
     */
    public void saveStudentsToFile() {
        fileLogger.saveStudentsToFile(students);
    }

    /**
     * Metod som anropar en annan metod, som visar allt innehåll från textfilen
     */
    public void displayStudentsFromFile() {
        fileLogger.displayStudentsFromFile();
    }

    /**
     * Metod som inhämtar ett student-ID och därefter letar efter en nyckel som matchar, vid träff raderas nyckel med värde/innehåll från listan (HasMap) och textfilen.
     * Användaren meddelas om raderingen lyckas eller inte.
     * @param studentId
     */
    public void removeStudent(int studentId) {
        if (students.containsKey(studentId)) {
            students.remove(studentId);
            fileLogger.removeStudentFromFile(studentId);
            System.out.printf("Student med ID %d har tagits bort\n", studentId);
        } else {
            System.out.printf("Student med ID %d finns inte\n", studentId);
        }
    }

    /**
     * Metod som tömmer hela listan (HashMap) samt raderar allt innehåll från textfilen. Användaren meddelas att raderingen har genomförts.
     */
    public void removeAllStudents() {
        students.clear();
        fileLogger.removeAllStudentsInFile();
        System.out.println("Alla studenter har nu tagits bort från samtliga listor");
    }

    /**
     * Getter för students listan (HashMap)
     * @return students
     */
    public Map<Integer, Student> getStudents() {
        return students;
    }
}
