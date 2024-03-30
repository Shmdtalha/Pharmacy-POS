package View;

import Model.Entity.SalesAssistant;
import Service.SessionInfo;

import javax.swing.*;

public class MainDashboardView extends BaseView{

    private JPanel ui;

    private JButton createNewSaleButton;

    private JButton productCatalogButton;

    private JButton inventoryButton;

    private JButton reportButton;

    public MainDashboardView(String title) {
        super(title);
    }

    @Override
    protected void initializeComponents() {



        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ui = new JPanel();
        createNewSaleButton = new JButton("New Sale");
        productCatalogButton = new JButton("Product Catalog");
        inventoryButton = new JButton("Inventory");
        reportButton = new JButton("Report");

        ui.add(createNewSaleButton);
        ui.add(inventoryButton);
        ui.add(reportButton);

        add(ui);
        //System.out.println(SessionInfo.getUserInstance().getUsername());

        if(SessionInfo.getUserInstance().getRole() instanceof SalesAssistant){
            inventoryButton.setVisible(false);
            reportButton.setVisible(false);
        }
    }

    public JPanel getUi() {
        return ui;
    }

    public void setUi(JPanel ui) {
        this.ui = ui;
    }

    public JButton getCreateNewSaleButton() {
        return createNewSaleButton;
    }

    public void setCreateNewSaleButton(JButton createNewSaleButton) {
        this.createNewSaleButton = createNewSaleButton;
    }

    public JButton getProductCatalogButton() {
        return productCatalogButton;
    }

    public void setProductCatalogButton(JButton productCatalogButton) {
        this.productCatalogButton = productCatalogButton;
    }

    public JButton getInventoryButton() {
        return inventoryButton;
    }

    public void setInventoryButton(JButton inventoryButton) {
        this.inventoryButton = inventoryButton;
    }

    public JButton getReportButton() {
        return reportButton;
    }

    public void setReportButton(JButton reportButton) {
        this.reportButton = reportButton;
    }
}
