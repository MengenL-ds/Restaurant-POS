package persistence;

import javafx.scene.control.Tab;
import model.Beverages;
import model.Dishes;
import model.Table;
import model.Restaurant;
import model.exceptions.GetBeverageByIDException;
import model.exceptions.GetDishByIDException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    private Restaurant lartduchef;
    private Restaurant chicchiDiCaffeDorati;

    @Test
    void testWriterInvalidFile() {
        try {
            Restaurant r = new Restaurant("Lart Du Chef");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroomAndEmptyTables() {
        try {
            Restaurant r = new Restaurant("My Restaurant");
            Table t1 = new Table(1);
            Table t50 = new Table(50);
            JsonWriter writer = new JsonWriter("./data/testEmptyRestaurant.json");
            writer.open();
            writer.write(r);
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmptyRestaurant.json");
            r = reader.read();
            assertEquals("My Restaurant", r.getResName());
            assertEquals(1, t1.getTableNumber());
            assertEquals(50, t50.getTableNumber());
            assertTrue(t1.getOrderDrinks().isEmpty());
            assertTrue(t1.getOrderFood().isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (GetDishByIDException e) {
            e.printStackTrace();
        } catch (GetBeverageByIDException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testWriterTables() {
        try {
            Restaurant r = new Restaurant("Kitchen");
            Table t1 = new Table(1);
            Table t2 = new Table(1);
            r.addNewTable(t1);
            r.addNewTable(t2);
            JsonWriter writer = new JsonWriter("./data/testWriterTables.json");
            writer.open();
            writer.write(r);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterTables.json");
            r = reader.read();
            assertEquals("Kitchen", r.getResName());
            List<Table> tableTotal = r.getTableList();
            assertEquals(50, tableTotal.size());
            checkTableNumber(1, r.getTableList().get(0));
            checkTableNumber(2, r.getTableList().get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (GetDishByIDException e) {
            e.printStackTrace();
        } catch (GetBeverageByIDException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testWriterFoodAndBeverage() {
        try {
            lartduchef = new Restaurant("Lart Du Chef");
            Restaurant r = new Restaurant("Lart Du Chef");
            Table t1 = new Table(1);
            Table t2 = new Table(1);
            Beverages b1 = new Beverages("Sangria", 69, 12.99);
            Dishes d1 = new Dishes("Scalop", "seafood", 1, 90, 19.99);
            r.addNewTable(t1);
            r.addNewTable(t2);
            Table table1 = r.getTableList().get(0);
            table1.addNewBeverage(b1);
            table1.addNewDish(d1);

            JsonWriter writer = new JsonWriter("./data/testWriterFoodAndBeverage" + ".json");
            writer.open();
            writer.write(r);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterFoodAndBeverage.json");
            r = reader.read();

            assertEquals("Sangria", r.getTableList().get(0).getOrderDrinks().get(0).getBeverageName());
            assertEquals("Scalop", r.getTableList().get(0).getOrderFood().get(0).getDishName());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (GetDishByIDException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GetBeverageByIDException e) {
            e.printStackTrace();
        }
    }
}