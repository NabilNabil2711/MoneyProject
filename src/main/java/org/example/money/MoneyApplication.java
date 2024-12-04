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

    private String DB_URL = "jdbc:sqlite:/Users/nabil.nabil/Code/MoneyProject/Budget.db";
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

    public int getUserId(String userName, String password) throws SQLException {
        Connection connection = null;
        int userId = -1;
        try {
            connection = new Connectdb(DB_URL).getConnection();


            PreparedStatement preparedStatement = connection.prepareStatement("SELECT PASSWORD_DB, ID_DB FROM LOGIN WHERE userName_DB = ?");
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("PASSWORD_DB");

                if (storedPassword.equals(password)) {
                    userId = resultSet.getInt("ID_DB");
                } else {
                    System.out.println("Incorrect password.");
                }
            } else {
                addUser(connection, userName, password);
                 }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Log the exception
                }
            }
        }

        return userId;
    }

    private void addUser(Connection connection, String userName, String password) throws SQLException {

        String insertSql = "INSERT INTO LOGIN (userName_DB, PASSWORD_DB) VALUES (?, ?)";

        try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
            insertStatement.setString(1, userName);
            insertStatement.setString(2, password);

            int rowsAffected = insertStatement.executeUpdate();
            if (rowsAffected > 0) {
               int userID =  getUserId(userName, password);
               AddBudget(userID,"SalaryRest", 990);
            } else {

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}