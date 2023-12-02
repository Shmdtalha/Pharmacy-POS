package Model;

import Model.DAO.CategoryDAO;
import Model.Entity.Category;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryDAOTest {

    private static CategoryDAO categoryDAO;

    @BeforeAll
    static void initializeDAO(){
        categoryDAO = new CategoryDAO();
    }


    @Test
    void testUpdateCategory() {
        Category category = new Category("code123", "Sample Category", "Test Category");
        category.setParentCategory(category);
        categoryDAO.save(category);

        // Update category details
        category.setName("Updated Category");
        categoryDAO.update(category, category.getCode());

        // Retrieve the updated category and verify changes
        List<Category> categories = categoryDAO.loadAll();
        for(Category c: categories){
            if(c.getCode().equals(category.getCode())){
                assertEquals("Updated Category", c.getName());
            }
        }


    }


}
