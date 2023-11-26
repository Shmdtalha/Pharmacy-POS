import Service.InventoryService;
import View.InventoryView;
import View.POSView;

public class Main {
    public static void main(String[] args) {
       // new InventoryService(new InventoryView("Inventory View"));
        new POSView("Point Of Sales");
    }
}