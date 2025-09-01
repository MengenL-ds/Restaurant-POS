package model;

import model.exceptions.GetBeverageByIDException;
import model.exceptions.GetDishByIDException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantTest {
    private Restaurant res1;
    private Table t1;
    private Table t50;
    private Dishes cornishCod;
    private Beverages sangria;
    private LartduchefMenu menu;

    @BeforeEach
    public void setup() {
        res1 = new Restaurant("Lart Du Chef");
        t1 = new Table(1);
        t50 = new Table(50);
        sangria = new Beverages("Sangria", 142, 6.99);
        cornishCod = new Dishes("Cornish Cod", "Seafood", 2, 12, 21.99);
        menu = new LartduchefMenu();
    }

    @Test
    public void testConstructor() {
        assertEquals(50, res1.getTableList().size());
        assertEquals("Lart Du Chef", res1.getResName());
    }

    @Test
    public void addNewTableTest() {
        res1.addNewTable(t1);
        assertEquals(1, res1.getTableList().get(0).getTableNumber());
        res1.occupiedTables.add(1);
        assertFalse(res1.addNewTable(t1));
    }


    @Test
    public void availableTablesTest() {
        res1.availableTables();
        assertEquals(50, res1.getRemainingAvailableTables().size());
        res1.addNewTable(t1);
    }

    @Test
    public void testServeDishToTableNoExceptions() throws GetDishByIDException {
        try {
            res1.serveDishToTable(2, 1);
            assertEquals("Isle Of Skye Scallop" ,res1.getTableList().get(1).getOrderFood().get(0).getDishName());
        } catch (GetDishByIDException e) {
            fail();
        }
    }

    @Test
    public void testServeDishToTableYesExceptions() throws GetDishByIDException {
        try {
            res1.serveDishToTable(90, 5);
        } catch (GetDishByIDException e) {
            //expected
        }
    }

    @Test
    public void testServeBeverageToTableNoException() throws GetBeverageByIDException{
        try {
            res1.serveBeverageToTable(1, 1);
            assertEquals("Jean Foillard Morgon Côte du Py" ,
                    res1.getTableList().get(1).getOrderDrinks().get(0).getBeverageName());
        } catch (GetBeverageByIDException e) {
            fail();
        }
    }

    @Test
    public void testServeBeverageToTableYesException() throws GetBeverageByIDException{
        try {
            res1.serveBeverageToTable(99, 5);
            assertEquals("" ,res1.getTableList().get(4).getOrderDrinks().get(0).getBeverageName());
        } catch (GetBeverageByIDException e) {
            //expected
        }
    }

    @Test
    public void testOccupiedTables() throws GetBeverageByIDException, GetDishByIDException {
        assertTrue(res1.occupiedTables().isEmpty());
        res1.getTableList().get(0).getOrderDrinks().add(menu.getBeverageByID(1));
        res1.occupiedTables();
        assertEquals(1, res1.occupiedTables.size());
        res1.getTableList().get(49).getOrderFood().add(menu.getDishByID(1));
        res1.occupiedTables();
        assertEquals(2, res1.occupiedTables.size());
        assertEquals(2, res1.occupiedTables.size());
    }

    @Test
    public void testCheckOutTable() throws GetDishByIDException, GetBeverageByIDException {
        assertTrue(res1.occupiedTables().isEmpty());
        assertFalse(res1.getRemainingAvailableTables().contains(1));
        res1.serveDishToTable(1, 1);
        res1.serveBeverageToTable(1, 1);
        res1.availableTables();
        res1.checkOutTable(1);
        assertTrue(res1.getRemainingAvailableTables().contains(1));
    }
}
