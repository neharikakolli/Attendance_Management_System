import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Student {
    private String id;
    private String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

class AttendanceManager {
    private ArrayList<Student> students;
    private Map<String, Boolean> attendanceRecords;

    public AttendanceManager() {
        students = new ArrayList<>();
        attendanceRecords = new HashMap<>();
    }

    public void addStudent(String id, String name) {
        students.add(new Student(id, name));
        System.out.println("Student added: " + name);
    }

    public void markAttendance(String studentId, boolean present) {
        if (!studentExists(studentId)) {
            System.out.println("Student ID not found. Please enter a valid student ID.");
            return;
        }
        attendanceRecords.put(studentId, present);
        System.out.println("Attendance recorded for student ID: " + studentId + " - Present: " + present);
    }

    public void viewAttendance() {
        System.out.println("\nAttendance Records:");
        for (Student student : students) {
            String id = student.getId();
            String status = attendanceRecords.getOrDefault(id, false) ? "Present" : "Absent";
            System.out.println("Student ID: " + id + ", Name: " + student.getName() + ", Attendance: " + status);
        }
    }

    public void listStudents() {
        System.out.println("\nStudents:");
        for (Student student : students) {
            System.out.println("ID: " + student.getId() + ", Name: " + student.getName());
        }
    }

    private boolean studentExists(String studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return true;
            }
        }
        return false;
    }
}

public class AttendanceApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AttendanceManager manager = new AttendanceManager();

        while (true) {
            System.out.println("\nAttendance Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Mark Attendance");
            System.out.println("3. View Attendance");
            System.out.println("4. List Students");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    manager.addStudent(id, name);
                    break;

                case 2:
                    System.out.print("Enter Student ID to mark attendance: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Is the student present? (true/false): ");
                    boolean present = scanner.nextBoolean();
                    manager.markAttendance(studentId, present);
                    scanner.nextLine(); // Consume newline
                    break;

                case 3:
                    manager.viewAttendance();
                    break;

                case 4:
                    manager.listStudents();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
