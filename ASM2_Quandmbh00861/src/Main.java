import java.util.*;

class Student {
    private String id;
    private String name;
    private double marks;
    private String rank;

    public Student(String id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.rank = calculateRank(marks);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public String getRank() {
        return rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(double marks) {
        this.marks = marks;
        this.rank = calculateRank(marks);
    }

    private String calculateRank(double marks) {
        if (marks < 5.0) return "Fail";
        else if (marks < 6.5) return "Medium";
        else if (marks < 7.5) return "Good";
        else if (marks < 9.0) return "Very Good";
        else return "Excellent";
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Marks: %.2f | Rank: %s", id, name, marks, rank);
    }
}

class StudentManagement {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void editStudent(String id, String name, double marks) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                student.setName(name);
                student.setMarks(marks);
                return;
            }
        }
    }

    public void deleteStudent(String id) {
        students.removeIf(student -> student.getId().equals(id));
    }

    public List<Student> sortStudentsByMarks() {
        students.sort(Comparator.comparingDouble(Student::getMarks).reversed());
        return students;
    }

    public Student searchStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) return student;
        }
        return null;
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagement management = new StudentManagement();

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Sort Students by Marks");
            System.out.println("6. Search Student by ID");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (option) {
                case 1 -> {
                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks = scanner.nextDouble();
                    management.addStudent(new Student(id, name, marks));
                }
                case 2 -> {
                    System.out.print("Enter ID to Edit: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter New Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter New Marks: ");
                    double marks = scanner.nextDouble();
                    management.editStudent(id, name, marks);
                }
                case 3 -> {
                    System.out.print("Enter ID to Delete: ");
                    String id = scanner.nextLine();
                    management.deleteStudent(id);
                }
                case 4 -> management.displayAllStudents();
                case 5 -> {
                    System.out.println("Sorted Students:");
                    management.sortStudentsByMarks();
                    management.displayAllStudents();
                }
                case 6 -> {
                    System.out.print("Enter ID to Search: ");
                    String id = scanner.nextLine();
                    Student student = management.searchStudentById(id);
                    if (student != null) System.out.println(student);
                    else System.out.println("Student not found.");
                }
                case 7 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
}
