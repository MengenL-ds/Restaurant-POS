/*
This class represents a restaurant
*/

package model;

import model.exceptions.GetBeverageByIDException;
import model.exceptions.GetDishByIDException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Restaurant implements Writable {
    public final List<Table> tableTotal;
    public final ArrayList<Integer> remainingAvailableTables;
    public final HashSet<Integer> occupiedTables;
    public final String name;
    public final LartduchefMenu lartduchefMenu;

    // EFFECTS: Constructs a list with a fixed number of cells (50)
    public Restaurant(String name) {
        tableTotal = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            tableTotal.add(new Table(i));
        }
        remainingAvailableTables = new ArrayList<>();
        this.name = name;
        lartduchefMenu = new LartduchefMenu();
        occupiedTables = new HashSet<>();
    }

    // REQUIRES: table < 50 (Max number of tables in the restaurant)
    // MODIFIES: this
    // EFFECTS: Creates a new table for customers and returns true, return false if table already occupied.
    public boolean addNewTable(Table t) {
        if (!occupiedTables.contains(t.getTableNumber())) {
            tableTotal.set(t.getTableNumber() - 1, t);
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: Determines which tables are currently occupied AND with items in food or drinks list
    public HashSet<Integer> occupiedTables() {
        for (Table t : tableTotal) {
            if (!(t.getOrderFood().isEmpty()) || (!(t.getOrderDrinks().isEmpty()))) {
                occupiedTables.add(t.getTableNumber() - 1);
            }
        }
        return occupiedTables;
    }

    // MODIFIES: this
    // EFFECTS: Determines which tables are vacant
    public void availableTables() {
        getRemainingAvailableTables().clear();
        for (int i = 0; i < tableTotal.size(); i++) {
            if (tableTotal.get(i).getOrderFood().isEmpty() && tableTotal.get(i).getOrderDrinks().isEmpty()) {
                remainingAvailableTables.add(i);
            }
        }
    }

    // REQUIRES: 0 <= table <= 50
    // MODIFIES: this
    // EFFECTS: Serves a dish given id to a given table
    public void serveDishToTable(int id, int table) throws GetDishByIDException {
        Dishes d1 = lartduchefMenu.getDishByID(id);
        tableTotal.get(table).addNewDish(d1);
    }

    // REQUIRES: 0 <= table <= 50
    // MODIFIES: this
    // EFFECTS: Serves a beverage given id to a given table
    public void serveBeverageToTable(int id, int table) throws GetBeverageByIDException {
        Beverages b1 = lartduchefMenu.getBeverageByID(id);
        tableTotal.get(table).addNewBeverage(b1);
    }

    // REQUIRES: Table number must be available inside tableTotal
    // EFFECTS: Prints the bill for that table, removes the table from the occupied list.
    public void checkOutTable(int tableNum) {
        getTableList().set(tableNum, new Table(tableNum));
        occupiedTables.remove(tableNum);
        getTableList().get(tableNum).getOrderFood().clear();
        getTableList().get(tableNum).getOrderDrinks().clear();
        EventLog.getInstance().logEvent(new Event("Table: " + tableNum + " has checked out from " + name
                + ", all dishes and be beverages have been cleared"));
    }

    public List<Integer> getRemainingAvailableTables() {
        return remainingAvailableTables;
    }

    public List<Table> getTableList() {
        return tableTotal;
    }

    public String getResName() {
        return this.name;
    }

    // <--------------------------------------------------JSON----------------------------------------------------------

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("tables", tableToJson());
        return json;
    }

    private JSONArray tableToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Table t : tableTotal) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }

}
