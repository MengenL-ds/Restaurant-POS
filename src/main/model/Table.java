/*
This class represents a table
*/

package model;

import model.exceptions.GetBeverageByIDException;
import model.exceptions.GetDishByIDException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class Table implements Writable {
    private final ArrayList<Dishes> orderFood;
    private final ArrayList<Beverages> orderDrinks;
    private final int tableNumber;
    private double subtotal;
    private double tax;

    // EFFECTS: Constructs a Table with given fields
    public Table(int tableNumber) {
        orderFood = new ArrayList<>();
        orderDrinks = new ArrayList<>();
        this.tableNumber = tableNumber;
    }

    public ArrayList<Dishes> getOrderFood() {
        return orderFood;
    }

    public ArrayList<Beverages> getOrderDrinks() {
        return orderDrinks;
    }

    public int getTableNumber() {
        return this.tableNumber;
    }

    // MODIFIES: this
    // EFFECTS: adds the newly ordered beverage to the list of beverages ordered
    public void addNewDish(Dishes d) throws GetDishByIDException {
        orderFood.add(d);
        EventLog.getInstance().logEvent(new Event("Dish:" + d.getDishName() + " has been added to table: "
                + (tableNumber - 1)));
    }

    // EFFECTS: Returns a list with all the dishes that have been ordered
    public List<String> orderedFoods() {
        ArrayList<String> orderedFood = new ArrayList<>();
        for (Dishes d : orderFood) {
            orderedFood.add(d.getDishName());
        }
        return orderedFood;
    }

    // EFFECTS: Returns a list with all the drinks that have been ordered
    public List<String> orderedDrinks() {
        ArrayList<String> orderedDrinks = new ArrayList<>();
        for (Beverages b : orderDrinks) {
            orderedDrinks.add(b.getBeverageName());
        }
        return orderedDrinks;
    }

    // MODIFIES: this
    // EFFECTS: adds the newly ordered beverage to the list of beverages ordered
    public void addNewBeverage(Beverages b) throws GetBeverageByIDException {
        orderDrinks.add(b);
        EventLog.getInstance().logEvent(new Event("Drink:" + b.getBeverageName() + " has been added to table: "
                + (tableNumber - 1)));
    }

    // MODIFIES: foodSubtotal, drinksSubtotal.
    // EFFECTS: Calculates and returns the total price of the meal before tax and gratuity.
    public double subtotal() {
        double foodSubtotal = 0;
        double drinksSubtotal = 0;
        for (Dishes dishes : orderFood) {
            foodSubtotal += dishes.getDishPrice();
        }
        for (Beverages orderDrink : orderDrinks) {
            drinksSubtotal += orderDrink.getBeveragePrice();
        }
        this.subtotal = foodSubtotal + drinksSubtotal;
        return foodSubtotal + drinksSubtotal;
    }

    // EFFECT: Calculates the tax amount of a given meal.
    public double tax() {
        this.tax = 0.20 * this.subtotal;
        return this.tax;
    }

    // EFFECTS: Calculates the total amount needed to pay for the meal
    public double total() {
        return this.tax + this.subtotal;
    }

    //----------------------------------------------------------JSON----------------------------------------------------
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("number", this.tableNumber);
        json.put("dishes", dishesToJson());
        json.put("beverages", beveragesToJson());
        return json;
    }

    private JSONArray dishesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Dishes d : orderFood) {
            jsonArray.put(d.toJson());
        }
        return jsonArray;
    }

    private JSONArray beveragesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Beverages b : orderDrinks) {
            jsonArray.put(b.toJson());
        }
        return jsonArray;
    }
}
