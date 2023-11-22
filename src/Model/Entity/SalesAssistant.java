package Model.Entity;

public class SalesAssistant extends Role {
    private Order[] orders;

    @Override
    public void permissions() {

    }

    // Getters and Setters
    public Order[] getOrders() { return orders; }
    public void setOrders(Order[] orders) { this.orders = orders; }
}