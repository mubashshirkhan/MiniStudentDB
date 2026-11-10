import jdk.swing.interop.SwingInterOpUtils;

import java.util.Scanner;

public class StudentUI {
    private static Scanner sc = new Scanner(System.in);
    private static StudentDB db = new StudentDB();

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== STUDENT MINI DB =====");
            System.out.println("1. Add");
            System.out.println("2. Count");
            System.out.println("3. Print");
            System.out.println("4. Search");
            System.out.println("5. Retrieve");
            System.out.println("6. Update");
            System.out.println("7. Remove");
            System.out.println("8. Replace");
            System.out.println("9. Insert");
            System.out.println("0. Exit");

            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1 -> {
                    db.add(readStudent());
                    System.out.println("Student is registered");
                }
                case 2 -> System.out.println("Total: " + db.count());
                case 3 -> {
                    if (db.isEmpty())
                        System.out.println("Error: No students are registered");
                    else
                        db.getAll().forEach(System.out::println);
                }
                case 4 -> search();
                case 5 -> retrieve();
                case 6 -> update();
                case 7 -> remove();
                case 8 -> replace();
                case 9 -> insert();
                case 0 -> System.exit(0);
                default -> System.out.println("Invalid Choice");
            }
        }
    }

    private static Student readStudent() {

        System.out.print("Sno: ");
        int sno = sc.nextInt();
        sc.nextLine();

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Course: ");
        String course = sc.nextLine();

        System.out.print("Fee: ");
        double fee = sc.nextDouble();
        sc.nextLine();

        return new Student(sno, name, course, fee);
    }

    private static void search() {

        System.out.print("Sno: ");
        int sno = sc.nextInt();
        sc.nextLine();

        System.out.print("Course: ");
        String course = sc.nextLine();

        System.out.println(
                db.exists(sno, course) ? "Student Exists" : "Not Found");
    }

    private static void retrieve() {

        System.out.print("Sno: ");
        int sno = sc.nextInt();
        sc.nextLine(); // Consume newline

        System.out.print("Course: ");
        String course = sc.nextLine().trim();

        Student s = db.retrieve(sno, course);

        if (s != null) {
            System.out.println(s);
        } else {
            System.out.println("Student Not Found!");
        }
    }

    private static void update() {

        System.out.print("Sno: ");
        int sno = sc.nextInt();
        sc.nextLine();

        System.out.print("Course: ");
        String course = sc.nextLine();

        System.out.print("New Name: ");
        String name = sc.nextLine();

        System.out.print("New Fee: ");
        double fee = sc.nextDouble();

        System.out.println(
                db.update(sno, name, course, fee) ?
                        "Updated Successfully" : "Not Found");
    }

    private static void remove() {

        System.out.print("Sno: ");
        int sno = sc.nextInt();
        sc.nextLine();

        System.out.print("Course: ");
        String course = sc.nextLine();

        System.out.println(
                db.remove(sno, course) ?
                        "Removed Successfully" : "Not Found");
    }

    private static void replace() {

        System.out.print("Index: ");
        int index = sc.nextInt();
        sc.nextLine();

        System.out.println(
                db.replace(index, readStudent()) ?
                        "Replaced Successfully" : "Invalid Index");
    }

    private static void insert() {

        System.out.print("Index: ");
        int index = sc.nextInt();
        sc.nextLine();

        System.out.println(
                db.insert(index, readStudent()) ?
                        "Inserted Successfully" : "Invalid Index");
    }

}
