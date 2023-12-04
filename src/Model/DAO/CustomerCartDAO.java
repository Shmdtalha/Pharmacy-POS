package Model.DAO;

import Model.Entity.CustomerCart;
import Model.Entity.Item;
import Util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * The CustomerCartDAO (Data Access Object) class handles database operations related to customer carts.
 * This includes creating customer carts with products and managing their related database interactions.
 */
public class CustomerCartDAO {

    /**
     * The method generates a unique timestamp-based ID for each cart, inserts the cart
     * into the CustomerCarts table, and inserts each product in the cart into the CartProducts table.
     * It also updates the stock quantity of each product in the cart.
     *
     * @param customerCart The customer cart to be created in the database.
     * @param productDAO The ProductDAO instance for handling product-related operations.
     * @throws SQLException if there is an error in database access or query execution.
     */
    public void createCustomerCartWithProducts(CustomerCart customerCart, ProductDAO productDAO) throws SQLException {
        // Generate timestamp-based ID
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmss");
        String datetime = ft.format(new Date());

        // Use the generated ID for cartId
        customerCart.setCartId(datetime);

        String insertCartQuery = "INSERT INTO CustomerCarts (cartId, customerName, totalAmount) VALUES (?, ?, ?)";
        String insertCartProductQuery = "INSERT INTO CartProducts (cartId, productCode, quantity, productPrice) VALUES (?, ?, ?, ?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pstCart = conn.prepareStatement(insertCartQuery);
            PreparedStatement pstCartProduct = conn.prepareStatement(insertCartProductQuery)
        )
        {
            // Insert the cart with the generated ID
            pstCart.setString(1, customerCart.getCartId());
            pstCart.setString(2, customerCart.getCustomerName());
            pstCart.setDouble(3, customerCart.getTotalAmount());
            int affectedRowsCart = pstCart.executeUpdate();
            System.out.println(pstCart);

            if (affectedRowsCart == 0) {
                throw new SQLException("Creating customer cart failed, no rows affected.");
            }

            List<Item> cartItems = customerCart.getItems();
            // Insert the order products and decrease product quantity
            for (Item item : cartItems) {
                pstCartProduct.setString(1, customerCart.getCartId());
                pstCartProduct.setString(2, item.getProductCode());
                pstCartProduct.setInt(3, item.getQuantity());
                pstCartProduct.setDouble(4, item.getProductPrice());
                pstCartProduct.addBatch();

                // Decrease the product quantity in stock
                productDAO.decreaseProductQuantity(item.getProductCode(), item.getQuantity());
            }
            pstCartProduct.executeBatch();
            System.out.println(pstCartProduct);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
