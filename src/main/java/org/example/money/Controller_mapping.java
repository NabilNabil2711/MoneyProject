package org.example.money;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class Controller_mapping {
    MoneyApplication application=new MoneyApplication();


    //Call website
    //------------------------
    @GetMapping("testHtml")
    public String hello() {
        return "index";
    }
    @GetMapping("calculate")
    public String calculate() {
        return "Calculator";
    }
    @GetMapping("addKathegorie")
    public String addKathegorie()
    {
        return "BudgetForm";
    }

    //Get input from User
    //-------------------------
    @PostMapping("/getSalary")
    @ResponseBody
    public String getSalary(@RequestParam("salary")int salary,@RequestParam("savingsrate")int savingsrate) {
        List<String> savingsResults = application.calculateSavings(savingsrate);
        StringBuilder response = new StringBuilder("Das eingegebene Gehalt ist: " + salary + "\nErsparnisse:\n");
        for (String result : savingsResults) {
            response.append(result).append("\n");
        }

        return response.toString();
    }
}
