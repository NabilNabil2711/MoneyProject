package org.example.money;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MoneyApplication {
    static int counter = 0;

    public static int getCounter() {
        counter =9;
        return counter;
    }

    public static void main(String[] args) {
        SpringApplication.run(MoneyApplication.class, args);
        counter=getCounter();
        while (counter < 10) {
            System.out.println("Hello"+counter);
            counter++;
        }

    }

}
