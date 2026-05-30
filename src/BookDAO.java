import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    // 1. ADD A BOOK
    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, author, category, total_copies, available_copies) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setInt(4, book.getTotalCopies());
            ps.setInt(5, book.getAvailableCopies());
            ps.executeUpdate();
            System.out.println("✅ Book added successfully!");
            con.close();
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // 2. VIEW ALL BOOKS
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try {
            Connection con = DatabaseConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getInt("total_copies"),
                        rs.getInt("available_copies")
                );
                books.add(book);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        return books;
    }

    // 3. DELETE A BOOK
    public void deleteBook(int bookId) {
        String sql = "DELETE FROM books WHERE book_id = ?";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, bookId);
            ps.executeUpdate();
            System.out.println("✅ Book deleted successfully!");
            con.close();
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}