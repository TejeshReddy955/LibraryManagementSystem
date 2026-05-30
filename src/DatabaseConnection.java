import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/library_db";
            String user = "root";
            String password = "Tejesh@123"; // ⚠️ put your MySQL password here
            // if no password just leave it empty
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to MySQL Successfully!");
            return con;
        } catch (Exception e) {
            System.out.println("❌ Connection Failed: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        getConnection();
    }
}