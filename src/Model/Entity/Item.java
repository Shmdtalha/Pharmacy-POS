package Model.Entity;

public class Item {
    String productCode, productName;
    private int quantity;
    private double totalPrice;
    private double productPrice;


    public Item(int quantityOrdered, double productPrice, double totalPrice, String productCode, String productName) {
        this.quantity = quantityOrdered;
        this.totalPrice = totalPrice;
        this.productPrice = productPrice;
        this.productCode = productCode;
        this.productName = productName;
    }

    public Item() {

    }

    public int getQuantity() {
        return quantity;
    }

    public double total() {
        return quantity * productPrice;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String getProductCode() {
        return productCode;
    }

}