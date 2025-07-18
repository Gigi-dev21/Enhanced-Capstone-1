package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:8889/Account_Ledger";
        String user = "root";
        String password = "root";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL database!");

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
