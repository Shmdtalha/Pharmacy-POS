package Service;
import Model.DAO.UserDAO;
import View.BaseView;
import View.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginService extends BaseService{
    private LoginView loginView;

    private UserDAO userDAO;

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
                if(username == ""){
                    //create a label which displays the error
                    return;
                }
                String password = loginView.getPasswordTextField().getText();
                if(password == ""){
                    //create a label to display the error
                    return;
                }

                //checks if person with username and password exists
            }
        });
    }

    @Override
    public void refreshView() {
        loginView.getUsernameTextField().setText("");
        loginView.getPasswordTextField().setText("");
    }
}
