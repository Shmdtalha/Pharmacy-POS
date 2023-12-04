package Model.DAO;

import Model.Entity.Category;
import Model.Entity.Product;
import Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The ProductDAO (Data Access Object) class is responsible for handling all database operations
 * related to products. This includes saving, updating, inserting, deleting products, and managing
 * product expiry information.
 */
public class ProductDAO{

    /**
     * Saves a product to the database. If the product code exists, it updates the product's
     * quantity and price, otherwise, it inserts a new product.
     *
     * @param p The product to be saved.
     */
    public void save(Product p) {
        if (productCodeExists(p.getCode())) {
            updateProductQuantityAndPrice(p); // Update existing product's quantity price
        } else {
            insertProduct(p); // Insert new product
        }
    }

    /**
     * Checks if a product code exists in the database.
     *
     * @param productCode The product code to check.
     * @return True if the product code exists, false otherwise.
     */
    public boolean productCodeExists(String productCode) {
        String query = "SELECT COUNT(*) FROM Products WHERE productCode = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, productCode);
            System.out.println(pst);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error checking product existence: " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }


    /**
     * Updates the quantity and price of an existing product in the database.
     *
     * @param p The product with updated information.
     */
    public void updateProductQuantityAndPrice(Product p) {
        String updateQuery = "UPDATE Products SET stockQuantity = stockQuantity + ?, price = ? WHERE productCode = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(updateQuery)) {

            pst.setInt(1, p.getStockQuantity()); // Increment stock quantity
            pst.setDouble(2, p.getPrice());
            pst.setString(3, p.getCode());

            System.out.println(pst);
            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                System.err.println("No rows affected, might indicate that the product does not exist.");
            } else {
                System.out.println("Product updated successfully.");
            }
        } catch (SQLException ex) {
            System.err.println("SQL error occurred while updating product: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


    /**
     * Inserts a new product into the database.
     *
     * @param p The new product to be inserted.
     */
    public void insertProduct(Product p) {
        String insertProductQuery = "INSERT INTO Products (productCode, productName, description, stockQuantity, price) VALUES (?,?,?,?,?)";
        String insertProductCategoryQuery = "INSERT INTO ProductCategories (productCode, categoryCode) VALUES (?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstProduct = conn.prepareStatement(insertProductQuery)) {

            pstProduct.setString(1, p.getCode());
            pstProduct.setString(2, p.getName());
            pstProduct.setString(3, p.getDescription());
            pstProduct.setInt(4, p.getStockQuantity());
            pstProduct.setDouble(5, p.getPrice());
            System.out.println(pstProduct);
            int affectedRows = pstProduct.executeUpdate();

            if (affectedRows == 0) {
                System.err.println("Creating product failed, no rows affected.");
                return;
            }


            try (PreparedStatement pstProductCategory = conn.prepareStatement(insertProductCategoryQuery)) {
                for (Category category : p.getCategories()) {
                    pstProductCategory.setString(1, p.getCode());
                    pstProductCategory.setString(2, category.getCode());
                    System.out.println(pstProductCategory);
                    pstProductCategory.executeUpdate();
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public List<Product> getProductsByCategories(List<Category> categories) {
        List<Product> products = new ArrayList<>();
        if (categories == null || categories.isEmpty()) {
            return products;
        }

        String categoryCodes = categories.stream()
                .map(Category::getCode)
                .map(code -> "'" + code + "'")
                .collect(Collectors.joining(","));

        String query = "SELECT DISTINCT p.* FROM Products p " +
                "JOIN ProductCategories pc ON p.productCode = pc.productCode " +
                "WHERE pc.categoryCode IN (" + categoryCodes + ")";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getString("productCode"),
                        rs.getString("productName"),
                        rs.getString("description"),
                        rs.getInt("stockQuantity"),
                        rs.getDouble("price")
                );
                products.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }




    /**
     * Deletes a product from the database using the product object.
     *
     * @param p The product to be deleted.
     */
    public void delete(Product p) {
        String deleteProductQuery = "DELETE FROM Products WHERE ProductCode = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstProduct = conn.prepareStatement(deleteProductQuery)) {

            pstProduct.setString(1, p.getCode());
            int affectedRows = pstProduct.executeUpdate();

            if (affectedRows == 0) {
                System.err.println("Deleting product failed, no rows affected.");
            } else {
                System.out.println("Product deleted successfully.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Deletes a product from the database using a product code.
     *
     * @param productCode The code of the product to be deleted.
     */
    public void delete(String productCode) {
        String deleteProductQuery = "DELETE FROM Products WHERE ProductCode = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstProduct = conn.prepareStatement(deleteProductQuery)) {

            pstProduct.setString(1, productCode);
            int affectedRows = pstProduct.executeUpdate();

            if (affectedRows == 0) {
                System.err.println("Deleting product failed, no rows affected.");
            } else {
                System.out.println("Product deleted successfully.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Product> searchProductsByName(String name) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Products WHERE productName LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, "%" + name + "%");
            System.out.println(pst);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    products.add(new Product(
                            rs.getString("productCode"),
                            rs.getString("productName"),
                            rs.getString("description"),
                            rs.getInt("stockQuantity"),
                            rs.getDouble("price")
                    ));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return products;
    }

    public void insertExpiryInfo(String productCode, int batchNumber, Date expiryDate, String location) throws SQLException {
        String insertExpiryQuery = "INSERT INTO expiryTable (productCode, batchNumber, expiryDate, location) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(insertExpiryQuery)) {
            System.out.println(pst);

            pst.setString(1, productCode);
            pst.setInt(2, batchNumber);
            pst.setDate(3, expiryDate);
            pst.setString(4, location);

            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inserting expiry info failed, no rows affected.");
            }
        }
    }

    public List<String> getProductsNearExpiry() {
        List<String> nearExpiryProducts = new ArrayList<>();
        String query = "SELECT productName, expiryDate, batchNumber, location FROM Products " +
                "JOIN ExpiryTable ON Products.productCode = ExpiryTable.productCode " +
                "WHERE expiryDate BETWEEN CURRENT_DATE AND DATE_ADD(CURRENT_DATE, INTERVAL 30 DAY)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {



            while (rs.next()) {
                String productName = rs.getString("productName");
                Date expiryDate = rs.getDate("expiryDate");
                nearExpiryProducts.add(productName + " - Batch " + rs.getInt("batchNumber") + " - Location: " + rs.getString("location") + " - " + expiryDate.toString());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nearExpiryProducts;
    }


    public int getStockQuantity(String productCode) throws SQLException {
        String query = "SELECT stockQuantity FROM Products WHERE productCode = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, productCode);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("stockQuantity");
                }
                return 0; // Return 0 if product not found
            }
        }
    }

    public void decreaseProductQuantity(String productCode, int quantity) throws SQLException {
        String updateQuery = "UPDATE Products SET stockQuantity = stockQuantity - ? WHERE productCode = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(updateQuery);

            pst.setInt(1, quantity);
            pst.setString(2, productCode);

            System.out.println(pst);
            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating product quantity failed, no rows affected.");
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }


    public void deleteExpiryInfo(String productCode, String batchNumber, String location) throws SQLException {
        String deleteQuery = "DELETE FROM ExpiryTable WHERE productCode = ?" +
                (batchNumber.isEmpty() ? "" : " AND batchNumber = ?") +
                (location.isEmpty() ? "" : " AND location = ?");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(deleteQuery)) {


            pst.setString(1, productCode);
            int paramIndex = 2;
            if (!batchNumber.isEmpty()) {
                pst.setString(paramIndex++, batchNumber);
            }
            if (!location.isEmpty()) {
                pst.setString(paramIndex, location);
            }

            System.out.println(pst);
            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No expiry information found for the specified criteria.");
            }
        }
    }

    public List<String> getAllExpiryInfo() {
        List<String> allExpiryInfo = new ArrayList<>();
        String query = "SELECT productName, expiryDate, batchNumber, location FROM Products " +
                "JOIN ExpiryTable ON Products.productCode = ExpiryTable.productCode";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                String productName = rs.getString("productName");
                Date expiryDate = rs.getDate("expiryDate");
                allExpiryInfo.add(productName + " - Batch " + rs.getInt("batchNumber") + " - Location: " + rs.getString("location") + " - " + expiryDate.toString());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return allExpiryInfo;
    }



}


