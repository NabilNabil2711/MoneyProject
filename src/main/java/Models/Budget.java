package Models;

public class Budget
{
    private int id;
    private String category; // This can be an enum if categories are fixed
    private int budget;

    // Constructor, getters, and setters
    public Budget(int id, String category, int amount) {
        this.id = id;
        this.category = category;
        this.budget = amount;
    }
    // Getters
    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public double getBudget() {
        return budget;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
}


