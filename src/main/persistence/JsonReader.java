package persistence;

import javafx.scene.text.TextAlignment;
import model.Beverages;
import model.Dishes;
import model.Restaurant;
import model.Table;
import model.exceptions.GetBeverageByIDException;
import model.exceptions.GetDishByIDException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Restaurant read() throws IOException, GetDishByIDException, GetBeverageByIDException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRestaurant(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses restaurant from JSON object and returns it
    private Restaurant parseRestaurant(JSONObject jsonObject) throws GetDishByIDException, GetBeverageByIDException {
        String name = jsonObject.getString("name");
        Restaurant r = new Restaurant(name);
        addNewTables(r, jsonObject);
        return r;
    }

    private void addNewTables(Restaurant r, JSONObject jsonObject) throws GetDishByIDException,
            GetBeverageByIDException {
        JSONArray jsonArray = jsonObject.getJSONArray("tables");
        for (Object json : jsonArray) {
            JSONObject nextTable = (JSONObject) json;
            addNewTable(r, nextTable);
        }
    }

    private void addNewTable(Restaurant r, JSONObject jsonObject) throws GetDishByIDException,
            GetBeverageByIDException {
        Table t = new Table(jsonObject.getInt("number"));
        addNewDishes(t, jsonObject);
        addNewDrinks(t, jsonObject);
        r.addNewTable(t);
    }

    private void addNewDishes(Table t, JSONObject jsonObject) throws GetDishByIDException {
        JSONArray jsonArrayDish = jsonObject.getJSONArray("dishes");
        for (Object json : jsonArrayDish) {
            JSONObject nextDish = (JSONObject) json;
            addNewDish(t, nextDish);
        }
    }

    private void addNewDish(Table t, JSONObject jsonObject) throws GetDishByIDException {
        Dishes d = new Dishes(
                jsonObject.getString("dishName"),
                jsonObject.getString("dishCategory"),
                jsonObject.getInt("dishCourse"),
                jsonObject.getInt("dishID"),
                jsonObject.getDouble("dishPrice"));
        t.addNewDish(d);
    }

    private void addNewDrinks(Table t, JSONObject jsonObject) throws GetBeverageByIDException {
        JSONArray jsonArrayDrinks = jsonObject.getJSONArray("beverages");
        for (Object json : jsonArrayDrinks) {
            JSONObject nextDrink = (JSONObject) json;
            addNewDrink(t, nextDrink);
        }
    }

    private void addNewDrink(Table t, JSONObject jsonObject) throws GetBeverageByIDException {
        Beverages b = new Beverages(jsonObject.getString("drinkName"), jsonObject.getInt("drinkID"),
                jsonObject.getDouble("drinkPrice"));
        t.addNewBeverage(b);
    }
}