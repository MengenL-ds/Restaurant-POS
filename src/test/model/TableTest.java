package model;

import model.exceptions.GetBeverageByIDException;
import model.exceptions.GetDishByIDException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TableTest {
    private Table table1;
    private Table table3;
    private Beverages sangria;
    private Dishes cornishCod;
    private Restaurant Lartduchef;

    @BeforeEach
    public void setup() {
        Lartduchef = new Restaurant("Lart Du Chef");
        table1 = new Table(5);
        table3 = new Table(2);
        sangria = new Beverages("Sangria", 142, 6.99);
        cornishCod = new Dishes("Cornish Cod", "Seafood", 2, 12, 21.99);
    }

    @Test
    public void testConstructor() {
        assertEquals(5, table1.getTableNumber());
        assertEquals(2, table3.getTableNumber());
    }

    @Test
    public void addNewDishTest() throws GetDishByIDException {
        assertTrue(table1.getOrderFood().isEmpty());
        table1.addNewDish(cornishCod);
        assertFalse(table1.getOrderFood().isEmpty());
    }

    @Test
    public void addNewBeverageTest() throws GetBeverageByIDException {
        assertTrue(table1.getOrderDrinks().isEmpty());
        table1.addNewBeverage(sangria);
        assertFalse(table1.getOrderDrinks().isEmpty());
    }

    @Test
    public void getTableNumberTest() {
        assertEquals(5, table1.getTableNumber());
        assertTrue(table3.getTableNumber() != 4);
    }

    @Test
    public void totalTest() {
        assertEquals(0, table1.subtotal());
        assertEquals(0 ,table1.tax());
        assertEquals(0, table1.total());
        table1.getOrderFood().add(cornishCod);
        table1.getOrderDrinks().add(sangria);
        table1.getOrderDrinks().add(sangria);
        assertEquals(35.97, table1.subtotal());
        assertEquals(7.194 ,table1.tax());
        assertEquals(43.164, table1.total());
    }

    @Test
    public void testOrderedFoodsAndOrderedDrinks() {
        table1.getOrderFood().add(cornishCod);
        assertEquals("Cornish Cod", table1.orderedFoods().get(0));
        table1.getOrderDrinks().add(sangria);
        assertEquals("Sangria", table1.orderedDrinks().get(0));
    }
}
