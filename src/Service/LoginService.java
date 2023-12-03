package Service;
import Model.DAO.UserDAO;
import Model.Entity.User;
import View.BaseView;
import View.LoginView;
import View.MainDashboardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginService extends BaseService{
    private LoginView loginView;

    private final UserDAO userDAO;

    public LoginService(BaseView view) {
        super(view);
        userDAO = new UserDAO();
        refreshView();
    }

    @Override
    public void loadDialogBoxes() {

    }

    @Override
    protected void setChildReference() {
        loginView = ((LoginView)view);
    }

    @Override
    protected void checkViewType() {
        try{
            if(!(loginView instanceof LoginView)){
                throw new Exception("Expected Login View");
            }
        }
        catch(Exception ex){

        }

    }

    @Override
    protected void addListeners() {

        loginView.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginView.getUsernameTextField().getText();
                if(username.isEmpty()){
                    loginView.showError("Username can not be empty!");
                    return;
                }
                String password = loginView.getPasswordTextField().getText();
                if(password.isEmpty()){
                    loginView.showError("Password can not be empty!");
                    return;
                }

                User u = userDAO.findUser(username, password);

                if(u == null){
                    //display error
                }
                else{
                    SessionInfo.setLoggedInUser(u);
                }

                loginView.getPasswordTextField().setText("");
                loginView.getUsernameTextField().setText("");

                //panelName.revalidate();
                //panelName.repaint();
                loginView.remove(loginView.getUiPanel());
                loginView.revalidate();
                loginView.repaint();
                new MainDashboardService(new MainDashboardView("Main Dashboard"));
            }
        });
    }

    @Override
    public void refreshView() {
        loginView.getUsernameTextField().setText("");
        loginView.getPasswordTextField().setText("");
    }
}
