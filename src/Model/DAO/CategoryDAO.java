package Model.DAO;

import Model.Entity.Category;
import Util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The CategoryDAO (Data Access Object) class is responsible for handling all database operations
 * related to categories. This includes saving, updating, loading, and deleting category records.
 */
public class CategoryDAO{

    /**
     * Saves a new category to the database. Inserts a category with its details into the Categories table.
     *
     * @param c The category to be saved.
     */
    public void save(Category c) {
        String insertCategoryQuery = "INSERT INTO Categories (categoryCode, categoryName, description, parentCategoryCode) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(insertCategoryQuery)) {

            pst.setString(1, c.getCode());
            pst.setString(2, c.getName());
            pst.setString(3, c.getDescription());
            pst.setString(4, c.getParentCategory().getCode());

            // Execute the insert statement
            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                System.err.println("Creating category failed, no rows affected.");
            } else {
                System.out.println("pst");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Loads all categories from the database. Retrieves all category records from the Categories table.
     *
     * @return A list of all categories.
     */
    public List<Category> loadAll() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM Categories";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            System.out.println(pst);

            while (rs.next()) {
                Category category = new Category(
                        rs.getString("categoryCode"),
                        rs.getString("categoryName"),
                        rs.getString("description")
                );
              //category.setParentCategoryCode(rs.getString("parentCategoryCode"));
                categories.add(category);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return categories;
    }

    /**
     * Updates a category in the database. Modifies the details of an existing category in the Categories table.
     *
     * @param category The category with updated information.
     * @param oldCode The original code of the category to be updated.
     */
    public void update(Category category, String oldCode) {
        String sql = "UPDATE categories SET categoryName = ?, description = ?, categoryCode = ? WHERE categoryCode = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setString(3, category.getCode());
            statement.setString(4, oldCode);
            System.out.println(statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a category from the database. Removes a category record from the Categories table.
     *
     * @param c The category to be deleted.
     */
    public void delete(Category c) {
        String deleteCategoryQuery = "DELETE FROM Categories WHERE categoryCode = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(deleteCategoryQuery)) {

            pst.setString(1, c.getCode());
            System.out.println(pst);
            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                System.err.println("Deleting category failed.");
            } else {
                System.out.println("Category deleted successfully.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
