package Model.Entity;

import Model.DAO.CustomerCartDAO;

import java.sql.SQLException;

public class CustomerCart extends ItemContainer{

    private String cartId;
    private String customerName;
    private double totalAmount;

    public CustomerCart(){
        super();
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }

    public void generateOrder(){

    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void clear(){
        cartId= null;
        customerName = null;
        totalAmount = 0;
        items.clear();
    }


}
