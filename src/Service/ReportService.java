package Service;
import View.BaseView;
import View.LoginView;
import View.ReportView;

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

    }

    @Override
    public void refreshView() {

    }
}
