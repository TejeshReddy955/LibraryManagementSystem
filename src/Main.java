import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookDAO bookDAO = new BookDAO();
        StudentDAO studentDAO = new StudentDAO();
        BorrowDAO borrowDAO = new BorrowDAO();

        while (true) {
            System.out.println("\n=============================");
            System.out.println("  LIBRARY MANAGEMENT SYSTEM  ");
            System.out.println("=============================");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Delete Book");
            System.out.println("4. Add Student");
            System.out.println("5. View All Students");
            System.out.println("6. Delete Student");
            System.out.println("7. Borrow Book");
            System.out.println("8. Return Book");
            System.out.println("9. View All Borrow Records");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter Total Copies: ");
                    int copies = scanner.nextInt();
                    bookDAO.addBook(new Book(0, title, author, category, copies, copies));
                    break;

                case 2:
                    List<Book> books = bookDAO.getAllBooks();
                    System.out.println("\n--- All Books ---");
                    if (books.isEmpty()) {
                        System.out.println("No books found!");
                    } else {
                        for (Book b : books) {
                            System.out.println(b);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Book ID to delete: ");
                    int bookId = scanner.nextInt();
                    bookDAO.deleteBook(bookId);
                    break;

                case 4:
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = scanner.nextLine();
                    studentDAO.addStudent(new Student(0, name, email, phone));
                    break;

                case 5:
                    List<Student> students = studentDAO.getAllStudents();
                    System.out.println("\n--- All Students ---");
                    if (students.isEmpty()) {
                        System.out.println("No students found!");
                    } else {
                        for (Student s : students) {
                            System.out.println(s);
                        }
                    }
                    break;

                case 6:
                    System.out.print("Enter Student ID to delete: ");
                    int studentId = scanner.nextInt();
                    studentDAO.deleteStudent(studentId);
                    break;

                case 7:
                    System.out.print("Enter Student ID: ");
                    int borrowStudentId = scanner.nextInt();
                    System.out.print("Enter Book ID: ");
                    int borrowBookId = scanner.nextInt();
                    borrowDAO.borrowBook(borrowStudentId, borrowBookId);
                    break;

                case 8:
                    System.out.print("Enter Borrow Record ID to return: ");
                    int recordId = scanner.nextInt();
                    borrowDAO.returnBook(recordId);
                    break;

                case 9:
                    borrowDAO.viewAllRecords();
                    break;

                case 0:
                    System.out.println("👋 Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("❌ Invalid choice! Try again.");
            }
        }
    }
}