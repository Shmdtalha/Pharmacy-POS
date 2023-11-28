import Service.InventoryService;
import Service.LoginService;
import Service.POSService;
import View.InventoryView;
import View.LoginView;
import View.POSView;

public class Main {
    public static void main(String[] args) {
        new InventoryService(new InventoryView("Inventory View"));
        //new POSService(new POSView("Point Of Sales"));
        //new LoginService(new LoginView("Login"));
    }
}