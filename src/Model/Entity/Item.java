package Model.Entity;

public class Item {
    private int quantityOrdered;
    private double price;

    Product product;
    public int getQuantityOrdered() { return quantityOrdered; }
    public void setQuantityOrdered(int quantityOrdered) { this.quantityOrdered = quantityOrdered; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double total() {
        return quantityOrdered * price;
    }
}