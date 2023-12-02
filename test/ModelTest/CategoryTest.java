package ModelTest;

import Model.Entity.Category;
import Model.Entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    private Category mainCategory;
    private Category subCategory;
    private Product product;

    @BeforeEach
    void setUp() {
        mainCategory = new Category("MC", "Main Category", "Main Category is the Parent");
        subCategory = new Category("SC", "Sub Category", "SubCategory is sub of MainCategory");
        product = new Product("Product1", "Product Name", "Product is very expensive", 25, 34.04);
    }

    @Test
    void addSubcategory() {
        mainCategory.addSubcategory(subCategory);
        assertTrue(mainCategory.getSubcategories().contains(subCategory));
        assertEquals(mainCategory, subCategory.getParentCategory());
    }

    @Test
    void removeSubcategory() {
        mainCategory.addSubcategory(subCategory);
        mainCategory.removeSubcategory(subCategory);
        assertFalse(mainCategory.getSubcategories().contains(subCategory));
        assertNull(subCategory.getParentCategory());
    }

    @Test
    void addProduct() {
        mainCategory.addProduct(product);
        assertTrue(mainCategory.getProducts().contains(product));
        assertTrue(product.getCategories().contains(mainCategory));
    }

    @Test
    void removeProduct() {
        mainCategory.addProduct(product);
        mainCategory.removeProduct(product);
        assertFalse(mainCategory.getProducts().contains(product));
        assertFalse(product.getCategories().contains(mainCategory));
    }

    @Test
    void setParentCategory() {
        subCategory.setParentCategory(mainCategory);
        assertEquals(mainCategory, subCategory.getParentCategory());
        assertTrue(mainCategory.getSubcategories().contains(subCategory));
    }

    @Test
    void removeParentCategory() {
        subCategory.setParentCategory(mainCategory);
        subCategory.setParentCategory(null);
        assertNull(subCategory.getParentCategory());
        assertFalse(mainCategory.getSubcategories().contains(subCategory));
    }
    
}
