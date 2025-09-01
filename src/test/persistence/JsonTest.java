package persistence;

import model.Table;
import model.Beverages;
import model.Dishes;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTableNumber(int tableNumber, Table table) {
        assertEquals(tableNumber, table.getTableNumber());
    }

    protected void checkDrinks(String drinkName, int drinkID, double drinkPrice, Beverages drink) {
        assertEquals(drinkName, drink.getBeverageName());
        assertEquals(drinkID, drink.getBeverageID());
        assertEquals(drinkPrice, drink.getBeveragePrice());
    }

    protected void checkDishes(String dishName, String dishCategory, int dishCourse, int dishID, double dishPrice,
                               Dishes dish) {
        assertEquals(dishName, dish.getDishName());
        assertEquals(dishCategory, dish.getDishCategory());
        assertEquals(dishCourse, dish.getDishCourse());
        assertEquals(dishID, dish.getDishID());
        assertEquals(dishPrice, dish.getDishPrice());
    }


}
