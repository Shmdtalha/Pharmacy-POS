import Service.InventoryService;
import View.InventoryView;

public class Main {
    public static void main(String[] args) {
        new InventoryService(new InventoryView("Inventory View"));
    }
}