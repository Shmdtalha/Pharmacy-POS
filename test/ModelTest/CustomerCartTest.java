package ModelTest;

import Model.Entity.CustomerCart;
import Model.Entity.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerCartTest {

    private CustomerCart customerCart;

    @BeforeEach
    void setUp() {
        customerCart = new CustomerCart();
    }

    @Test
    void cartInitializationTest() {
        assertNull(customerCart.getCartId());
        assertNull(customerCart.getCustomerName());
        assertEquals(0, customerCart.getTotalAmount());
        assertTrue(customerCart.getItems().isEmpty());
    }

    @Test
    void setAndGetCartIdTest() {
        String cartId = "TestCart";
        customerCart.setCartId(cartId);
        assertEquals(cartId, customerCart.getCartId());
    }

    @Test
    void setAndGetCustomerNameTest() {
        String customerName = "Customer";
        customerCart.setCustomerName(customerName);
        assertEquals(customerName, customerCart.getCustomerName());
    }

    @Test
    void setAndGetTotalAmountTest() {
        double totalAmount = 123.45;
        customerCart.setTotalAmount(totalAmount);
        assertEquals(totalAmount, customerCart.getTotalAmount());
    }

    @Test
    void clearCartTest() {
        customerCart.setCartId("TestCart");
        customerCart.setCustomerName("Customer");
        customerCart.setTotalAmount(123.4);
        customerCart.add(new Item(6, 666.6, 6*666.6, "TP", "TestProduct"));

        customerCart.clear();

        assertNull(customerCart.getCartId());
        assertNull(customerCart.getCustomerName());
        assertEquals(0, customerCart.getTotalAmount());
        assertTrue(customerCart.getItems().isEmpty());
    }

    @Test
    void generateOrderTest() {
        customerCart.setCartId("TestCart");
        customerCart.setCustomerName("Customer");
        customerCart.setTotalAmount(123.4);

        assertDoesNotThrow(() -> customerCart.generateOrder());
    }

}
