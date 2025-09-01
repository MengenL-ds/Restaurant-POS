/*
This class represents a beverage
*/

package model;

import org.json.JSONObject;
import persistence.Writable;

public class Beverages implements Writable {
    private final String drinkName;       // Name of the dish
    private final int drinkID;            // ID of the drink
    private final double drinkPrice;      // Price of the drink in EUR

    // Constructor
    public Beverages(String name, int id, double price) {
        this.drinkName = name;
        this.drinkID = id;
        this.drinkPrice = price;
    }

    public String getBeverageName() {
        return drinkName;
    }

    public double getBeveragePrice() {
        return drinkPrice;
    }

    public int getBeverageID() {
        return drinkID;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("drinkName", drinkName);
        json.put("drinkID", drinkID);
        json.put("drinkPrice", drinkPrice);
        return json;
    }
}