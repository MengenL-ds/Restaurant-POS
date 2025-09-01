package model;

import model.exceptions.GetBeverageByIDException;
import model.exceptions.GetDishByIDException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class LartduchefMenuTest {
    public LartduchefMenu menu;

    @BeforeEach
    public void setup() {
        this.menu = new LartduchefMenu();
    }

    @Test
    public void testConstructor() {
        assertEquals(7, menu.lartduchefMenuBeverages.size());
        assertEquals(12, menu.lartduchefMenuDishes.size());
    }

    @Test
    public void getDishByIDTest() throws GetDishByIDException {
        assertEquals("Cumbrian Beef Tartare", menu.getDishByID(1).getDishName());
        assertEquals("Isle Of Skye Scallop", menu.getDishByID(2).getDishName());
    }

    @Test
    public void getDishByIDTestException() throws GetDishByIDException {
        try {
            menu.getDishByID(14);
        } catch (GetDishByIDException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getDishByIDTestNoException() throws GetDishByIDException {
        try {
            menu.getDishByID(12);
        } catch (GetDishByIDException e) {
            fail("This exception shouldn't have been thrown");
        }
    }

    @Test
    public void getBeverageByIDTest() throws GetBeverageByIDException {
        assertEquals("Dry Martini", menu.getBeverageByID(6).getBeverageName());
        assertEquals("Cocacola", menu.getBeverageByID(7).getBeverageName());
    }

    @Test
    public void getBeverageByIDTestException() throws GetBeverageByIDException {
        try {
            menu.getBeverageByID(8);
        } catch (GetBeverageByIDException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getBeverageByIDTestNoException() throws GetBeverageByIDException {
        try {
            menu.getBeverageByID(7);
        } catch (GetBeverageByIDException e) {
            fail("This exception shouldn't have been thrown");
        }
    }
}
