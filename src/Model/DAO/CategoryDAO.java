package Model.DAO;

import Model.Entity.Category;
import Util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO{

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

    public List<Category> loadAll() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM Categories";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

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


    public void delete(Category c) {

    }
}
