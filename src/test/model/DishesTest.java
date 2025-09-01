package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DishesTest {
    private Dishes cornishCod;
    private Dishes cremeBrulee;

    @BeforeEach
    public void setup() {
        cornishCod = new Dishes("Cornish Cod", "Seafood", 2, 345, 21.99);
        cremeBrulee = new Dishes("Creme Brulee", "Dessert", 3, 142, 10.99);
    }

    @Test
    public void constructorTest() {
        assertEquals("Cornish Cod", cornishCod.getDishName());
        assertEquals("Creme Brulee", cremeBrulee.getDishName());
        assertEquals(3, cremeBrulee.getDishCourse());
        assertEquals(2, cornishCod.getDishCourse());
        assertEquals(345, cornishCod.getDishID());
        assertEquals(142, cremeBrulee.getDishID());
        assertEquals(21.99, cornishCod.getDishPrice());
        assertEquals(10.99, cremeBrulee.getDishPrice());
        assertEquals("Seafood", cornishCod.getDishCategory());
        assertEquals("Dessert", cremeBrulee.getDishCategory());
    }

    @Test
    public void getDishNameTest() {
        assertEquals("Cornish Cod", cornishCod.getDishName());
        assertEquals("Creme Brulee", cremeBrulee.getDishName());
    }

    @Test
    public void getDishPriceTest() {
        assertEquals(21.99, cornishCod.getDishPrice());
        assertEquals(10.99, cremeBrulee.getDishPrice());
    }
}
