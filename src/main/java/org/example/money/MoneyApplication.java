package org.example.money;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
//SQL/DATABASE
import java.sql.*;
import Models.Connectdb;
@SpringBootApplication
public class MoneyApplication {

    private String DB_URL = "jdbc:sqlite:/Users/nabil.nabil/Code/Money/BUDGET.db";

    public static void main(String[] args) {
        SpringApplication.run(MoneyApplication.class, args);
        {
            System.out.println("Hello");
            MoneyApplication app = new MoneyApplication();
        }
    }

    public List<String> calculateSavings(int savingsRate) {
        int years = 12;
        int totalSavings = 0;
        List<String> results = new ArrayList<>();

        for (int year = 1; year <= years; year++) {
            totalSavings += savingsRate * 12;
            results.add("Jahr " + year + ": " + totalSavings);
        }

        return results;
    }

    public void AddBudget(int ID, String category, int budget) {
        Connection connection = null;

        try {
            connection = new Connectdb(DB_URL).getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO BUDGET (ID, Category, Budget) VALUES (?,?,?)");
            statement.setInt(1, ID);
            statement.setString(2, category);
            statement.setInt(3, budget);
            statement.executeUpdate();


        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void deleteFromBudget(int ID, String category) {
        Connection connection = null;

        try {
            connection = new Connectdb(DB_URL).getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM BUDGET WHERE ID = ? AND Category = ?");
            statement.setInt(1, ID);
            statement.setString(2, category);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet selectAllData(int id) {
        Connection connection = null;
        try {
            connection = new Connectdb(DB_URL).getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT CATEGORY,budget FROM BUDGET WHERE ID = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void CheckLoginData(int ID, String password) throws SQLException {
        // Verbindung zur LOGIN-Datenbank
        Connection connection = null;
        try {
            System.out.println("Checking login data...");
            connection = new Connectdb(DB_URL).getConnection();

            // Überprüfen, ob der Benutzer existiert
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT PASSWORD_DB FROM LOGIN WHERE ID_DB = ?");
            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Benutzer existiert, Passwort überprüfen
                String storedPassword = resultSet.getString("PASSWORD_DB"); // Korrigiert: Der Spaltenname sollte "PASSWORD_DB" sein
                if (storedPassword.equals(password)) {
                    System.out.println("Login successful!");
                } else {
                    System.out.println("Incorrect password.");
                }
            } else {
                // Benutzer existiert nicht, also neuen Benutzer hinzufügen
                addUser(connection, ID, password);
            }
        } catch (SQLException e) {
            System.out.println("SQL Fehler: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Ein unerwarteter Fehler ist aufgetreten: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Verbindung schließen
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Fehler beim Schließen der Verbindung: " + e.getMessage());
                }
            }
        }
    }

    private void addUser(Connection connection, int userId, String password) throws SQLException {
        System.out.println("Adding user " + userId);
        String insertSql = "INSERT INTO LOGIN (ID_DB, PASSWORD_DB) VALUES (?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
            insertStatement.setInt(1, userId);
            insertStatement.setString(2, password); // Passwort wird als Klartext gespeichert

            int rowsAffected = insertStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Benutzer erfolgreich hinzugefügt.");
            } else {
                System.out.println("Fehler beim Hinzufügen des Benutzers.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Fehler beim Hinzufügen des Benutzers: " + e.getMessage());
            e.printStackTrace();
        }
    }
}