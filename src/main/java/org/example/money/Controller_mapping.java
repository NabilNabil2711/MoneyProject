package org.example.money;
import Models.Budget;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class Controller_mapping {
    MoneyApplication application=new MoneyApplication();


   @GetMapping()
    public String hello(Model model) throws SQLException, JsonProcessingException {
       List<Budget> budgets= new ArrayList<>();

       try (ResultSet resultSet = application.selectAllData(1)) {
           while (resultSet.next()) {
               Budget Eintrag = new Budget(resultSet.getString("CATEGORY_DB"), resultSet.getInt("BUDGET_ID"));
               budgets.add(Eintrag);
           }
       }

       ObjectMapper objectMapper = new ObjectMapper();
       String json=objectMapper.writeValueAsString(budgets);
       model.addAttribute("budgets",json);
       return "index";
    }


    //Get input from User
    //-------------------------

    @PostMapping("/addBudget")
    public String getBudget(@RequestParam("budget_name")int budget,@RequestParam("Category") String Category)
    {
        application.AddBudget(1,Category,budget);
        return "redirect:/";
    }
    @PostMapping("/deleteBudget")
    public String deleteBudget(@RequestParam("Category")String Category){
        System.out.println(Category);
        application.deleteFromBudget(1,Category);
return "redirect:/";
    }

    //Get all data
    //Update list


}
