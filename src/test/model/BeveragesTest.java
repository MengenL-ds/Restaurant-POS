package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BeveragesTest {
    private Beverages dryMartini;
    private Beverages sangria;

    @BeforeEach
    public void setup() {
        dryMartini = new Beverages("Dry Martini", 1, 6.99);
        sangria = new Beverages("Sangria", 142, 7.99);
    }

    @Test
    public void constructorTest() {
        assertEquals("Dry Martini", dryMartini.getBeverageName());
        assertEquals("Sangria", sangria.getBeverageName());
        assertEquals(1, dryMartini.getBeverageID());
        assertEquals(142, sangria.getBeverageID());
        assertEquals(6.99, dryMartini.getBeveragePrice());
        assertEquals(7.99, sangria.getBeveragePrice());
    }

    @Test
    public void getBeverageNameTest() {
        assertEquals("Dry Martini", dryMartini.getBeverageName());
        assertEquals("Sangria", sangria.getBeverageName());
    }

    @Test
    public void getBeveragePriceTest() {
        assertEquals(6.99, dryMartini.getBeveragePrice());
        assertEquals(7.99, sangria.getBeveragePrice());
    }

    @Test
    public void getBeverageIDTest() {
        assertEquals(1 ,dryMartini.getBeverageID());
        assertEquals(142, sangria.getBeverageID());
    }
}
