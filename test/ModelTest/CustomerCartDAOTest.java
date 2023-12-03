package ModelTest;

import Model.DAO.CustomerCartDAO;
import Model.Entity.CustomerCart;
import Model.Entity.Item;
import Util.DBConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import static org.mockito.Mockito.*;

class CustomerCartDAOTest {

    @Mock private Connection connection;
    @Mock private PreparedStatement pstCart;
    @Mock private PreparedStatement pstCartProduct;
    private CustomerCartDAO customerCartDAO;
    private MockedStatic<DBConnection> mockedDBConnection;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        mockedDBConnection = Mockito.mockStatic(DBConnection.class);
        mockedDBConnection.when(DBConnection::getConnection).thenReturn(connection);

        when(connection.prepareStatement(anyString())).thenAnswer(invocation -> {
            String query = invocation.getArgument(0, String.class);
            return query.contains("CustomerCarts") ? pstCart : pstCartProduct;
        });

        customerCartDAO = new CustomerCartDAO();


        when(pstCart.executeUpdate()).thenReturn(1); // Indicates 1 row affected
        when(pstCartProduct.executeBatch()).thenReturn(new int[]{1, 1}); // Indicates each batch operation affected 1 row
    }

    @AfterEach
    void tearDown() throws Exception {
        mockedDBConnection.close();
    }

    @Test
    void testCreateCustomerCartWithProducts() throws SQLException {
        CustomerCart cart = new CustomerCart();
        cart.setCustomerName("Customer");
        cart.setItems(Arrays.asList(new Item(2, 9.99, 2*9.99, "P001", "Product1"), new Item(3, 19.99,3*19.99, "P002", "Product2")));

        customerCartDAO.createCustomerCartWithProducts(cart);

        verify(pstCart, times(1)).executeUpdate(); // Verify cart insertion
        verify(pstCartProduct, times(1)).executeBatch(); // Verify batch insert for cart products
    }


}
