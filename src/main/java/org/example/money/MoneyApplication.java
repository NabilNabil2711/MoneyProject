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
    private Connection connection;
    public static void main(String[] args) {
        SpringApplication.run(MoneyApplication.class, args);
        {
            System.out.println("Hello");
            MoneyApplication app = new MoneyApplication();
            app.connection = app.initializeConnection();

        }
    }

    //DB section
    private Connection initializeConnection() {
        connection = new Connectdb().getConnection();
        return connection;
    }
    public void AddBudget(int ID,String category, int budget) {


        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO BUDGET (ID, Category, Budget) VALUES (?,?,?)");
            statement.setInt(1, ID);
            statement.setString(2, category);
            statement.setInt(3, budget);
            statement.executeUpdate();


        }catch (Exception e) {

            e.printStackTrace();}
    }
    public void deleteFromBudget(int ID, String category){
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM BUDGET WHERE ID = ? AND Category = ?");
            statement.setInt(1, ID);
            statement.setString(2, category);
            statement.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public ResultSet selectAllData(int id) {
        Connection connection = null;
        try {
            connection = new Connectdb().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT CATEGORY,budget FROM BUDGET WHERE ID = ?");
            statement.setInt(1, id);
            return  statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

