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
            totalSavings += savingsRate*12;
            results.add("Jahr " + year + ": " + totalSavings);
        }

        return results;
    }
    public void AddBudget(int ID,String category, int budget)
    {
        Connection connection = null;

        try {
            connection = new Connectdb().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO BUDGET (ID, Category, Budget) VALUES (?,?,?)");
            statement.setInt(1, ID);
            statement.setString(2, category);
            statement.setInt(3, budget);
            statement.executeUpdate();


        }catch (Exception e) {

            e.printStackTrace();}
    }

    public void deleteFromBudget(int ID, String category){
        Connection connection = null;

        try{
            connection = new Connectdb().getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM BUDGET WHERE ID = ? AND Category = ?");
            statement.setInt(1, ID);
            statement.setString(2, category);
            statement.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}