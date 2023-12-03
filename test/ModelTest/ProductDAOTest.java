package ModelTest;

import Model.DAO.ProductDAO;
import Model.Entity.Product;
import Model.Entity.Category;
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
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductDAOTest {

    @Mock private Connection connection;
    @Mock private PreparedStatement preparedStatement;
    @Mock private ResultSet resultSet;
    private ProductDAO productDAO;
    private MockedStatic<DBConnection> mockedDBConnection;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        // DBConnection mocks
        mockedDBConnection = Mockito.mockStatic(DBConnection.class);
        mockedDBConnection.when(DBConnection::getConnection).thenReturn(connection);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        productDAO = new ProductDAO();
       // when(preparedStatement.executeUpdate()).thenReturn(1); // Indicates 1 row affected
    }



    @AfterEach
    void tearDown() throws Exception {
        mockedDBConnection.close();
    }


    @Test
    void testSaveProduct() throws Exception {
        Product mockProduct = new Product("P001", "PainAway", "Pain relief", 100, 9.99);
        Category mockCategory = new Category("C001", "Painkillers", "Kills the pain");
        mockProduct.addCategory(mockCategory);

        productDAO.save(mockProduct);

        verify(preparedStatement, times(1)).executeUpdate(); // For product insert query
        verify(preparedStatement, times(1)).executeUpdate(); // For category insert query
    }
    @Test
    void testGetProductsByCategories() throws Exception {
        List<Category> categories = Arrays.asList(
                new Category("C001", "Painkillers", "Killer of pain"),
                new Category("C002", "Wellness", "Well being")
        );

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getString("productCode")).thenReturn("P001").thenReturn("P002");
        when(resultSet.getString("productName")).thenReturn("PainAway").thenReturn("Wellness");
        when(resultSet.getString("description")).thenReturn("Pain relief").thenReturn("Wellness supplement");
        when(resultSet.getInt("stockQuantity")).thenReturn(100).thenReturn(50);
        when(resultSet.getDouble("price")).thenReturn(9.99).thenReturn(19.99);

        List<Product> products = productDAO.getProductsByCategories(categories);

        assertEquals(2, products.size());
        assertEquals("P001", products.get(0).getCode());
        assertEquals("P002", products.get(1).getCode());

        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    void testDeleteProduct() throws Exception {
        Product mockProduct = new Product("P01", "PaAway", "Pain ief", 1600, 9667.99);
        productDAO.delete(mockProduct);
        verify(preparedStatement, times(1)).executeUpdate();
    }


    @Test
    void testDeleteProductByCode() throws Exception {
        String productCode = "P00yct1";
        productDAO.delete(productCode);
        verify(preparedStatement, times(1)).executeUpdate();
    }


    @Test
    void testSearchProductsByName() throws Exception {
        String searchName = "Pain";

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("productCode")).thenReturn("P001");
        when(resultSet.getString("productName")).thenReturn("PainAway");
        when(resultSet.getString("description")).thenReturn("Pain relief");
        when(resultSet.getInt("stockQuantity")).thenReturn(100);
        when(resultSet.getDouble("price")).thenReturn(9.99);

        List<Product> products = productDAO.searchProductsByName(searchName);

        assertEquals(1, products.size());
        assertEquals("PainAway", products.get(0).getName());

        verify(preparedStatement, times(1)).executeQuery();
    }


}
