package org.example.money;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.ArrayList;
import java.util.List;
@SpringBootApplication
public class MoneyApplication {
    public int salary;

    public static void main(String[] args) {
        SpringApplication.run(MoneyApplication.class, args);
        {
            System.out.println("Hello");
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
}