package DB;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

import Classes.Transaction;

public class DBHandler {

    private static final String DB_URL = "jdbc:mysql://localhost:8889/Account_Ledger";  // update your port/db if different
    private static final String USER = "root";      // update if needed
    private static final String PASSWORD = "root";  // update if needed

    public static void saveTransactionToDB(Transaction t) {
        String sql = "INSERT INTO transactions (date, time, description, vendor, amount) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getDate().toString());
            stmt.setString(2, t.getTime().toString());
            stmt.setString(3, t.getDescription());
            stmt.setString(4, t.getVendor());
            stmt.setDouble(5, t.getAmount());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("\u001B[31m❌ Failed to save to the database: " + e.getMessage() + "\u001B[0m");
        }
    }
    
    public static void displayTransactionsFromDB() {
        String sql = "SELECT date, time, description, vendor, amount FROM transactions ORDER BY date DESC, time DESC";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                LocalDate date = rs.getDate("date").toLocalDate();
                LocalTime time = rs.getTime("time").toLocalTime();
                String description = rs.getString("description");
                String vendor = rs.getString("vendor");
                double amount = rs.getDouble("amount");

                Transaction t = new Transaction(date, time, description, vendor, amount);
                System.out.println(t);
            }

        } catch (SQLException e) {
            System.out.println("\u001B[31m❌ Error displaying transactions from DB: " + e.getMessage() + "\u001B[0m");
        }
    }
}
