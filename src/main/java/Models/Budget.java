package Models;

public class Budget
{

    private String category; // This can be an enum if categories are fixed
    private int budget;

    // Constructor, getters, and setters
    public Budget( String category, int amount) {

        this.category = category;
        this.budget = amount;
    }
    // Getters


    public String getCategory() {
        return category;
    }

    public double getBudget() {
        return budget;
    }



    public void setCategory(String category) {
        this.category = category;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
}


