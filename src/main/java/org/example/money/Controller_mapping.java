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


    MoneyApplication application = new MoneyApplication();
    int user_id;

    @GetMapping()
    public String Login() {
        return "loginSite";
    }


    @GetMapping("/loadData")
    public String hello(Model model) throws SQLException, JsonProcessingException {
        List<Budget> budgets = new ArrayList<>();
        try (ResultSet resultSet = application.selectAllData(user_id)) {
            while (resultSet.next()) {
                Budget Eintrag = new Budget(resultSet.getString("CATEGORY"), resultSet.getInt("budget"));
                budgets.add(Eintrag);
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(budgets);
        model.addAttribute("budgets", json);
        return "index";
    }


    //Get input from User
    //-------------------------

    @PostMapping("/addBudget")
    public String getBudget(@RequestParam("budget_name") int budget, @RequestParam("Category") String Category) {
        application.AddBudget(user_id, Category, budget);
        return "redirect:/loadData";
    }

    @PostMapping("/deleteBudget")
    public String deleteBudget(@RequestParam("Category") String Category) {
        System.out.println(Category);
        application.deleteFromBudget(user_id, Category);
        return "redirect:/loadData";
    }

    @PostMapping("/login")
    public String Login(@RequestParam("userName") String userName, @RequestParam("password") String password) throws SQLException {

        Integer userId = application.getUserId(userName, password);

        if (userId != null && userId != -1) {
            user_id = userId;
            return "redirect:/loadData";
        } else {
            return "loginSite";
        }
    }
}