package logger;
import student.Student;
import java.io.*;
import java.util.Map;

public class FileLogger {
    //Skapar en privat statisk final textfil
    private static final String studentFile = "students.txt";

    /**
     * Metoden som går igenom textfilen och sparar ner studentdata som sedan läggs till i listan (HashMap)
     * Om laddningen lyckas meddelas användaren, annars skrivs ett felmeddelande ut.
     * @param students
     */
    public void loadStudentsFromFile(Map<Integer, Student> students) {
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int studentId = Integer.parseInt(parts[0]);
                String studentName = parts[1];
                char studentGrade = parts[2].charAt(0);
                students.put(studentId, new Student(studentId, studentName, studentGrade));
            }
            System.out.println("Studentdata laddades från fil");
        } catch (IOException e) {
            System.out.println("Fel vid filinläsning: " + e.getMessage());
        }
    }

    /**
     * Metod som hämtar innehållet från listan (HashMap) och sparar i textfilen.
     * Om sparandet lyckas meddelas användaren, annars skrivs ett felmeddelande ut.
     * @param students
     */
    public void saveStudentsToFile(Map<Integer, Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(studentFile))) {
            for (Student student : students.values()) {
                writer.write(student.getStudentId() + "," + student.getStudentName() + "," + student.getStudentGrade() + "\n");
            }
            System.out.println("Studentdata sparades till fil");
        } catch (IOException e) {
            System.out.println("Fel vid filskrivning: " + e.getMessage());
        }
    }

    /**
     * Metod som läser och visar innehållet från textfilen. Användaren meddelas om filen är tom, och ett felmeddelande skrivs ut vid annat fel.
     */
    public void displayStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFile))) {
            String line;
            boolean isEmpty = true;

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    int studentId = Integer.parseInt(parts[0]);
                    String studentName = parts[1];
                    char studentGrade = parts[2].charAt(0);
                    System.out.printf("Student-ID: %d, Namn: %s, Betyg: %c\n", studentId, studentName, studentGrade);
                    isEmpty = false;
                }
            }

            if (isEmpty) {
                System.out.println("Filen är tom");
            }
        } catch (IOException e) {
            System.out.println("Fel vid filinläsning: " + e.getMessage());
        }
    }

    /**
     * Metod som inhämtar ett studentId, och därefter söker igenom textfilen rad för rad efter ett matchande student-ID.
     * Varje rad som inte matchar skrivs över i en tillfällig textfil. När hela den gamla textfilen gåtts igenom, raderas den gamla textfilen
     * och den tillfälliga textfilen byts ut mot den gamla. Ett felmeddelande skrivs ut till användaren vid fel.
     * @param studentId
     */
    public void removeStudentFromFile(int studentId) {
        try {
            File inputFile = new File(studentFile);
            File tempFile = new File("students_temp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) != studentId) {
                    writer.write(line + "\n");
                }
            }
            reader.close();
            writer.close();

            if (inputFile.delete()) {
                tempFile.renameTo(inputFile);
            }
        } catch (IOException e) {
            System.out.println("Fel vid borttagning av student från fil: " + e.getMessage());
        }
    }

    /**
     * Metod som raderar allt innehåll genom att sätta texten i filen till "". Ett felmeddelande skrivs ut till användaren vid fel.
     */
    public void removeAllStudentsInFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(studentFile))) {
            writer.write("");
        }
        catch (IOException e) {
            System.out.println("Fel vid rensning av fil: " + e.getMessage());
        }
    }
}
