package Service;
import View.BaseView;
import View.LoginView;
import View.MainDashboardView;
import View.ReportView;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ReportService extends BaseService{

    ReportView reportView;

    public ReportService(BaseView view) {
        super(view);
    }

    @Override
    public void loadDialogBoxes() {

    }

    @Override
    protected void setChildReference() {
        reportView = ((ReportView)view);
    }

    @Override
    protected void checkViewType() {

    }

    @Override
    protected void addListeners() {

        reportView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                reportView.setVisible(false);
                new MainDashboardService(new MainDashboardView("Main Dashboard"));
            }
        });
    }

    @Override
    public void refreshView() {

    }
}
