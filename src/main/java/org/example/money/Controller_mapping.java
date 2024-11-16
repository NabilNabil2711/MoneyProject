package org.example.money;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class Controller_mapping {
    MoneyApplication application=new MoneyApplication();


    //Call website
    //------------------------
    @GetMapping("testHtml")
    public String hello() {
        return "index";
    }


    //Get input from User
    //-------------------------

    @PostMapping("/getBudget")
    public String getBudget(@RequestParam("budget_name")int budget,@RequestParam("Category") String Category)
    {
        application.AddBudget(1,Category,budget);
        return "index";
    }
    @PostMapping("/deleteBudget")
    public String deleteBudget(@RequestParam("Category")String Category){
        System.out.println(Category);
        application.deleteFromBudget(1,Category);
        return "index";
    }
}
