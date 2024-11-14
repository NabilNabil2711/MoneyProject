package Models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectdb
{
    private Connection connection;
    private String DB_URL = "jdbc:sqlite:/Users/nabil.nabil/Code/Money/Budget.db";
    public Connectdb() {
        try {
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("Connected to the database successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return connection;
    }
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

