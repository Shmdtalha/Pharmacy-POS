package Model.Entity;

public class Item {
    private int quantity;
    private double totalPrice;
        private double productPrice;

    Product product;
        String productCode, productName;


    public Item(int quantityOrdered, double productPrice, double totalPrice, String productCode, String productName) {
        this.quantity = quantityOrdered;
        this.totalPrice =totalPrice;
        this.productPrice = productPrice;
        this.productCode = productCode;
        this.productName = productName;
    }

    public Item(){

    }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantityOrdered) { this.quantity = quantityOrdered; }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double price) { this.totalPrice = price; }

    public double total() {
        return quantity* productPrice;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }
}