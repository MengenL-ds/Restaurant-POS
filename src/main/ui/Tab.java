/*
    This class represents the supertype of all the tabs
*/

package ui;

import model.Restaurant;
import model.exceptions.GetBeverageByIDException;
import model.exceptions.GetDishByIDException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Tab extends JPanel {
    private Restaurant restaurant;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private static final String JSON_STORE = "./data/Restaurant.json";

    // EFFECTS: Constructor
    public Tab(Restaurant restaurant) {
        this.restaurant = restaurant;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    //--------------------------------------------------------JSON-----------------------------------------------------

    // EFFECTS: saves the restaurant to file
    protected void saveRestaurant() {
        try {
            jsonWriter.open();
            jsonWriter.write(restaurant);
            jsonWriter.close();
            System.out.println("Saved " + restaurant.getResName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads restaurant from file
    protected void loadRestaurant() {
        try {
            restaurant = jsonReader.read();
            System.out.println("Loaded " + restaurant.getResName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        } catch (GetDishByIDException | GetBeverageByIDException e) {
            e.printStackTrace();
        }
    }

}