package Service;
import Model.Entity.Product;
import View.BaseView;
import View.InventoryView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryService extends BaseService{
    public InventoryService(BaseView view)  {
        super(view);

    }

    @Override
    protected void checkViewType() {
        try{
        if (!(view instanceof InventoryView)) {
            throw new Exception("Expected Inventory View");
        }}
        catch(Exception ex){

        }
    }

    @Override
    protected void addListeners() {
        InventoryView inventoryView=((InventoryView)view);
        inventoryView.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    @Override
    public void refreshView() {

    }
}
