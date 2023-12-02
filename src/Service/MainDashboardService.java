package Service;
import View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainDashboardService extends BaseService{

    private MainDashboardView mainDashboardView;
    public MainDashboardService(BaseView view) {
        super(view);
    }

    @Override
    public void loadDialogBoxes() {

    }

    @Override
    protected void setChildReference() {
        if (view instanceof MainDashboardView) {
            mainDashboardView = (MainDashboardView) view;
        } else {
            throw new RuntimeException("View must be an instance of POSView");
        }
    }

    @Override
    protected void checkViewType() {

    }

    @Override
    protected void addListeners() {

        mainDashboardView.getCreateNewSaleButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mainDashboardView.setVisible(false);

                new POSService(new POSView("Sale"));
            }
        });

        mainDashboardView.getInventoryButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mainDashboardView.setVisible(false);

                new InventoryService(new InventoryView("Inventory"));
            }
        });

        mainDashboardView.getReportButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainDashboardView.setVisible(false);

                new ReportService(new ReportView("Report"));
            }
        });

        mainDashboardView.getProductCatalogButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    @Override
    public void refreshView() {

    }
}
