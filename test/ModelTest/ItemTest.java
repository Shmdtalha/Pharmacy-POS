package ModelTest;

import Model.Entity.Item;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void createItemAndTestGetters() {
        Item item = new Item(9, 99.99, 99.99*9, "P999", "Product 999");

        assertEquals(9, item.getQuantity());
        assertEquals(99.99, item.getProductPrice());
        assertEquals("P999", item.getProductCode());
    }

    @Test
    void testTotalCalculation() {
        Item item = new Item(3, 20.0, 60.0, "P999", "Product 999");
        assertEquals(60.0, item.total());
    }

    @Test
    void testConstructor() {
        Item item = new Item();
        assertEquals(0, item.getQuantity());
        assertEquals(0.0, item.getProductPrice());
        assertNull(item.getProductCode());
    }

}
