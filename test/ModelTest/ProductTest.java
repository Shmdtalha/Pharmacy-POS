package ModelTest;

import Model.Entity.Category;
import Model.Entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;
    private Category category1;
    private Category category2;

    @BeforeEach
    void setUp() {
        product = new Product("P001", "PainAway", "Fast-acting pain relief", 100, 9.99);
        category1 = new Category("C001", "Painkillers", "All varieties of pain relief");
        category2 = new Category("C002", "Wellness", "General health and wellness products");
    }

    @Test
    void testProductCreation() {
        assertEquals("P001", product.getCode());
        assertEquals("PainAway", product.getName());
        assertEquals("Fast-acting pain relief", product.getDescription());
        assertEquals(100, product.getStockQuantity());
        assertEquals(9.99, product.getPrice());
        assertTrue(product.getCategories().isEmpty());
    }

    @Test
    void testAddCategoryToProduct() {
        product.addCategory(category1);
        assertTrue(product.getCategories().contains(category1));
        assertTrue(category1.getProducts().contains(product));
    }

    @Test
    void testRemoveCategoryFromProduct() {
        product.addCategory(category1);
        product.removeCategory(category1);

        assertFalse(product.getCategories().contains(category1));
        assertFalse(category1.getProducts().contains(product));
    }

    @Test
    void testAddingMultipleCategories() {
        product.addCategory(category1);
        product.addCategory(category2);

        assertTrue(product.getCategories().contains(category1));
        assertTrue(product.getCategories().contains(category2));
        assertEquals(2, product.getCategories().size());
    }

    @Test
    void testProductToString() {
        String expectedOutput = "P001.PainAway: 100 / $9.99";
        assertEquals(expectedOutput, product.toString());
    }

}
