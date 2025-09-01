package persistence;

import model.Restaurant;
import model.exceptions.GetBeverageByIDException;
import model.exceptions.GetDishByIDException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Restaurant r = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        } catch (GetDishByIDException e) {
            e.printStackTrace();
        } catch (GetBeverageByIDException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testReaderRestaurant() {
        JsonReader reader = new JsonReader("./data/Restaurant.json");
        try {
            Restaurant r = reader.read();
            assertEquals("Lart Du Chef", r.getResName());
            assertEquals(50, r.getTableList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (GetDishByIDException e) {
            e.printStackTrace();
        } catch (GetBeverageByIDException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testWriterTables() {
        JsonReader reader = new JsonReader("./data/testWriterTables.json");
        try {
            Restaurant r = reader.read();
            assertEquals("Kitchen", r.getResName());
            assertEquals(50, r.getTableList().size());
            assertEquals(50, r.getTableList().size());
            checkTableNumber(1, r.getTableList().get(0));
            checkTableNumber(50, r.getTableList().get(49));
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (GetDishByIDException e) {
            e.printStackTrace();
        } catch (GetBeverageByIDException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testWriterFoodAndBeverage() {
        JsonReader reader = new JsonReader("./data/testWriterFoodAndBeverage.json");
        try {
            Restaurant r = reader.read();
            assertEquals("Lart Du Chef", r.getResName());
            checkDrinks("Sangria", 69, 12.99, r.getTableList().get(0).getOrderDrinks().get(0));
            checkDishes("Scalop", "seafood", 1, 90, 19.99, r.getTableList().get(0).getOrderFood().get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (GetDishByIDException e) {
            e.printStackTrace();
        } catch (GetBeverageByIDException e) {
            e.printStackTrace();
        }
    }
}