package org.example.money;
import Models.Budget;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class BudgetService
{
    MoneyApplication application = new MoneyApplication();

        public void getAllbudgets(Model model) throws JsonProcessingException {
            List<Budget> budgets = new ArrayList<>();
            try (ResultSet resultSet = application.selectAllData(1))
            {
                while (resultSet.next()) {
                    Budget eintrag = new Budget(resultSet.getString("CATEGORY_DB"), resultSet.getInt("BUDGET_ID"));
                    budgets.add(eintrag);
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

            ObjectMapper objectMapper = new ObjectMapper();
            String json=objectMapper.writeValueAsString(budgets);
            model.addAttribute("budgets",json);
        }

}