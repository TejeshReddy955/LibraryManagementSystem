import java.sql.*;

public class BorrowDAO {

    // 1. BORROW A BOOK
    public void borrowBook(int studentId, int bookId) {
        try {
            Connection con = DatabaseConnection.getConnection();

            // Check if book is available
            String checkSql = "SELECT available_copies FROM books WHERE book_id = ?";
            PreparedStatement checkPs = con.prepareStatement(checkSql);
            checkPs.setInt(1, bookId);
            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                int available = rs.getInt("available_copies");

                if (available > 0) {
                    // Insert borrow record
                    String borrowSql = "INSERT INTO borrow_records (student_id, book_id) VALUES (?, ?)";
                    PreparedStatement borrowPs = con.prepareStatement(borrowSql);
                    borrowPs.setInt(1, studentId);
                    borrowPs.setInt(2, bookId);
                    borrowPs.executeUpdate();

                    // Reduce available copies by 1
                    String updateSql = "UPDATE books SET available_copies = available_copies - 1 WHERE book_id = ?";
                    PreparedStatement updatePs = con.prepareStatement(updateSql);
                    updatePs.setInt(1, bookId);
                    updatePs.executeUpdate();

                    System.out.println("✅ Book borrowed successfully!");
                } else {
                    System.out.println("❌ Sorry! No copies available for this book.");
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // 2. RETURN A BOOK
    public void returnBook(int recordId) {
        try {
            Connection con = DatabaseConnection.getConnection();

            // Get book_id from borrow record
            String getSql = "SELECT book_id FROM borrow_records WHERE record_id = ?";
            PreparedStatement getPs = con.prepareStatement(getSql);
            getPs.setInt(1, recordId);
            ResultSet rs = getPs.executeQuery();

            if (rs.next()) {
                int bookId = rs.getInt("book_id");

                // Update return date and status
                String returnSql = "UPDATE borrow_records SET return_date = CURDATE(), status = 'returned' WHERE record_id = ?";
                PreparedStatement returnPs = con.prepareStatement(returnSql);
                returnPs.setInt(1, recordId);
                returnPs.executeUpdate();

                // Increase available copies by 1
                String updateSql = "UPDATE books SET available_copies = available_copies + 1 WHERE book_id = ?";
                PreparedStatement updatePs = con.prepareStatement(updateSql);
                updatePs.setInt(1, bookId);
                updatePs.executeUpdate();

                System.out.println("✅ Book returned successfully!");
            } else {
                System.out.println("❌ Record not found!");
            }
            con.close();
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // 3. VIEW ALL BORROW RECORDS
    public void viewAllRecords() {
        String sql = "SELECT br.record_id, s.name, b.title, " +
                "br.borrow_date, br.return_date, br.status " +
                "FROM borrow_records br " +
                "JOIN students s ON br.student_id = s.student_id " +
                "JOIN books b ON br.book_id = b.book_id";
        try {
            Connection con = DatabaseConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.println("\n--- All Borrow Records ---");
            while (rs.next()) {
                System.out.println(
                        "Record ID: " + rs.getInt("record_id") +
                                " | Student: " + rs.getString("name") +
                                " | Book: " + rs.getString("title") +
                                " | Borrowed: " + rs.getString("borrow_date") +
                                " | Return: " + rs.getString("return_date") +
                                " | Status: " + rs.getString("status")
                );
            }
            con.close();
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}