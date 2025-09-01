/*
This class represents a dish
*/

package model;

import org.json.JSONObject;
import persistence.Writable;

public class Dishes implements Writable {
    private String dishName;       // Name of the dish
    private String dishCategory;   // Category of the dish (Vegetarian, Vegan, Meats, Seafood, Dessert...)
    private int dishCourse;        // Course in which the customer would like to have the selected dish
    private int dishID;            // ID of the dish
    private double dishPrice;      // Price of the dish in EUR

    // Constructor
    public Dishes(String name, String category, int course, int id, double price) {
        this.dishName = name;
        this.dishCategory = category;
        this.dishCourse = course;
        this.dishID = id;
        this.dishPrice = price;
    }

    // Effects: Simple getters
    public String getDishName() {
        return dishName;
    }

    public double getDishPrice() {
        return dishPrice;
    }

    public int getDishID() {
        return dishID;
    }

    public String getDishCategory() {
        return dishCategory;
    }

    public int getDishCourse() {
        return dishCourse;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("dishName", dishName);
        json.put("dishCategory", dishCategory);
        json.put("dishCourse", dishCourse);
        json.put("dishID", dishID);
        json.put("dishPrice", dishPrice);
        return json;
    }
}
