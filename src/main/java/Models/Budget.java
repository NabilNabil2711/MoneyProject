package Models;

public class Budget
{

    private String category;
    private int budget;


    public Budget( String category, int amount) {

        this.category = category;
        this.budget = amount;
    }



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


