package ui;
import student.Student;
import student.StudentManager;
import java.util.Scanner;

public class Menu {
    //Skapar ett scanner objekt
    Scanner scan = new Scanner(System.in);
    //Skapar en instans av klassen StudentManager
    StudentManager studentManager = StudentManager.getInstance();
    //Skapar en boolean med värdet true
    boolean menuRunning = true;

    /**
     * Konstruktor som anropar en metod som visar menyn, samt stänger scannern efter programkörningen.
     */
    public Menu() {
        this.displayMenu();
        this.scan.close();
    }

    /**
     * Metod som först anropar en metod som laddar in all sparad data från textfil
     * Användarens menyval visas sedan och därefter anropas en metod som hanterar användarens val.
     */
    public void displayMenu() {
        studentManager.loadStudentsFromFile();

        while (menuRunning) {
            System.out.println("\n1. Lägg till ny student");
            System.out.println("2. Sök student via ID");
            System.out.println("3. Visa alla studenter (inklusive nytillagda)");
            System.out.println("4. Spara nytillagda studenter till fil");
            System.out.println("5. Visa alla sparade studenter från fil");
            System.out.println("6. Ta bort student");
            System.out.println("7. Radera alla studenter");
            System.out.println("8. Avsluta");
            System.out.print("\nVälj alternativ: ");

            menuChoice();
        }
    }

    /**
     * Metod som läser in användarens menyval, därefter (genom en switch-case) tillkallas olika metoder beroende på användarens val
     * En try catch fångar upp om användaren matat in något annat än ett heltal i menyvalet.
     */
    private void menuChoice() {
        try {
            String inputMenuChoice = scan.nextLine();
            System.out.println();
            int menuChoice = Integer.parseInt(inputMenuChoice);

            switch (menuChoice) {
                case 1:
                    this.addStudent();
                    break;
                case 2:
                    this.searchStudentById();
                    break;
                case 3:
                    studentManager.displayAllStudents();
                    break;
                case 4:
                    studentManager.saveStudentsToFile();
                    break;
                case 5:
                    studentManager.displayStudentsFromFile();
                    break;
                case 6:
                    this.removeStudent();
                    break;
                case 7:
                    this.removeAllStudents();
                    break;
                case 8:
                    this.closeProgram();
                    break;
                default:
                    System.out.println("Ogiltigt val, vänligen ange en siffra mellan 1 - 8");
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Ogiltigt val, vänligen ange en siffra mellan 1-8");
        }
    }

    /**
     * Metod som anropar andra metoder som inhämtar egenskaper och därefter
     * skickar med egenskaperna som argument i en metod som lägger till studenter i en lista
     * Metoden skriver även ut en bekräftelse till användaren när en student har lagts till.
     */
    private void addStudent() {
        int studentId = getStudentIdInput();
        String studentName = getStudentNameInput();
        char studentGrade = getStudentGradeInput();
        studentManager.addStudent(studentId, studentName, studentGrade);

        System.out.printf("Studenten \"%s\" har lagts till\n", studentName);
    }

    /**
     * Metod som ber användaren ange ett student-ID, samt kontrollerar att student-ID inte redan används
     * En try catch fångar upp om användaren matat in något annat än ett heltal i inmatningen.
     * @return studentId
     */
    private int getStudentIdInput() {
        while (true) {
            try {
                System.out.print("Ange student-ID (heltal): ");
                String inputStudentId = scan.nextLine();
                int studentId = Integer.parseInt(inputStudentId);

                if (studentManager.searchStudentById(studentId) != null) {
                    System.out.printf("Student-ID %d används redan\n\n", studentId);

                } else {
                    return studentId;
                }
            } catch (NumberFormatException e) {
                System.out.println("Felaktig inmatning, vänligen ange ett student-ID med siffror\n");
            }
        }
    }

    /**
     * Metod som ber användaren ange ett förnamn och efternamn, samt ändrar så de sparade världena har stor första bokstav och resternade text i små bokstäver
     * och slår därefter ihop namnen.
     * @return name
     */
    private String getStudentNameInput() {
        System.out.print("Ange förnamn: ");
        String firstName = scan.nextLine();
        firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();

        System.out.print("Ange efternamn: ");
        String lastName = scan.nextLine();
        lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();

        String studentName = firstName + " " + lastName;
        return studentName;
    }

    /**
     * Metod som ber användaren ange ett betyg, samt kontrollerar så det angivna betyget är en bokstav mellan A-F,
     * annars visas ett felmeddelande till användaren. Vid korrekt inmatning ändras det sparade värdet därefter till en stor bokstav.
     * @return grade
     */
    private char getStudentGradeInput() {
        while (true) {
            System.out.print("Ange betyg (A-F): ");
            char studentGrade = scan.next().charAt(0);
            studentGrade = Character.toUpperCase(studentGrade);
            scan.nextLine();

            if (Character.toUpperCase(studentGrade) >= 'A' && Character.toUpperCase(studentGrade) <= 'F') {
                return studentGrade;
            } else {
                System.out.println("Felaktig inmatning, ange en bokstav mellan A-F\n");
            }
        }
    }

    /**
     * Metod som först kontrollerar om listan med studenter är tom, annars inhämtas ett student-ID från användaren
     * som jämförs med nycklarna i listan. Vid träff hämtas värdet till nyckeln och visas för användaren,
     * annars meddelas användaren att student-ID inte finns i listan.
     * En try catch fångar upp om användaren matat in något annat än ett heltal i inmatningen av student-ID.
     */
    private void searchStudentById() {
        while (true) {
            try {
                if (studentManager.getStudents().isEmpty()) {
                    System.out.println("Listan är tom");
                    return;
                } else {
                    System.out.print("Ange student-ID att söka på: ");
                    String inputIdToSearch = scan.nextLine();
                    int idToSearch = Integer.parseInt(inputIdToSearch);
                    Student student = studentManager.searchStudentById(idToSearch);

                    if (student != null) {
                        System.out.println(student);
                        return;
                    } else {
                        System.out.printf("Student med ID %d hittades inte\n", idToSearch);
                        return;
                    }
                }
            }
            catch(NumberFormatException e){
                System.out.println("Felaktig inmatning, vänligen ange ett student-ID med siffror\n");
            }
        }
    }

    /**
     * Metod som inhämtar ett student-ID från användaren som jämförs med nycklarna i listan. Vid träff hämtas värdet till nyckeln och visas för användaren,
     * samt bes användaren bekräfta raderingen. Vid ja, tillkallas metod som raderar studenten från listan och textfilen. Vid nej, återgår användaren till menyn.
     * Vid annat svar än ja/nej visas ett felmeddelande samt upprepas frågan.
     * En try catch fångar upp om användaren matat in något annat än ett heltal i inmatningen av student-ID.
     */
    private void removeStudent() {
        while (true) {
            try {
                System.out.print("Ange student-ID att ta bort: ");
                String inputStudentId = scan.nextLine();
                int studentId = Integer.parseInt(inputStudentId);
                Student student = studentManager.searchStudentById(studentId);

                if (student != null) {
                    while (true) {
                        System.out.println("\n*** OBS! ***");
                        System.out.printf("Är du säker på att du vill ta bort \"%s\"? (ja/nej): ", student);
                        String confirmRemoveStudent = scan.nextLine();
                        if (confirmRemoveStudent.equalsIgnoreCase("ja")) {
                            studentManager.removeStudent(studentId);
                            return;
                        } else if (confirmRemoveStudent.equalsIgnoreCase("nej")) {
                            System.out.println("Ingen borttagning har genomförts");
                            return;
                        } else {
                            System.out.println("Felaktig inmatning, vänligen ange \"ja\" eller \"nej\"");
                        }
                    }
                } else {
                    System.out.printf("Student med ID %d hittades inte\n", studentId);
                    return;
                }

            } catch (NumberFormatException e) {
                System.out.println("Felaktig inmatning, vänligen ange ett student-ID med siffror\n");
            }
        }
    }

    /**
     * Metod där användaren först ombedes bekräfta raderingen. Vid ja, tillkallas metod som raderar allt från listan och textfilen. Vid nej, återgår användaren till menyn.
     * Vid annat svar än ja/nej visas ett felmeddelande samt upprepas frågan.
     */
    private void removeAllStudents() {
        while (true) {
            System.out.println("*** OBS! ***");
            System.out.print("Är du säker på att du vill radera alla studenter från listorna? (ja/nej): ");
            String confirmRemoveStudents = scan.nextLine();
            if (confirmRemoveStudents.equalsIgnoreCase("ja")) {
                studentManager.removeAllStudents();
                return;
            } else if (confirmRemoveStudents.equalsIgnoreCase("nej")) {
                System.out.println("Ingen borttagning har genomförts");
                return;
            } else {
                System.out.println("Felaktig inmatning, vänligen ange \"ja\" eller \"nej\"\n");
            }
        }
    }

    /**
     * Metod som först visar användaren en påminnelse om att spara data innan avslut, därefter ombedes användaren bekräfta om programmet ska stängas ner.
     * Vid ja, stängs programmet genom att sätta boolean värdet för menyn till false. Vid nej, återgår användaren till menyn.
     * Vid annat svar än ja/nej visas ett felmeddelande samt upprepas frågan.
     */
    private void closeProgram () {
        System.out.println("*** OBS! ***");
        System.out.println("Om du inte har sparat nytillagda studenter till filen (genom menyval 4), kommer studentposterna inte sparas i programmet efter avslut!");
        while (true) {
            System.out.print("Är du säker på att du vill avsluta programmet? (ja/nej): ");
            String confirmCloseProgram = scan.nextLine();

            if (confirmCloseProgram.equalsIgnoreCase("nej")) {
                System.out.println("Återgår till menyn");
                return;
            }
            else if (confirmCloseProgram.equalsIgnoreCase("ja")) {
                System.out.println("\nProgrammet avslutas");
                menuRunning = false;
                return;
            }
            else {
                System.out.println("Felaktig inmatning, vänligen ange \"ja\" eller \"nej\"\n");
            }
        }
    }
}
