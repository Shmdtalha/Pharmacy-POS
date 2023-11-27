package View;

import javax.swing.*;
import java.awt.*;

public class LoginView extends BaseView{

    private JPanel uiPanel;

    private JLabel usernameLabel;

    private JLabel passwordLabel;

    private JTextField usernameTextField;

    private JTextField passwordTextField;

    private JButton loginButton;

    private JPanel usernamePanel;

    private JPanel passwordPanel;

    public LoginView(String title) {
        super(title);
    }

    @Override
    protected void initializeComponents() {

        setLayout(new BorderLayout());
        usernameLabel = new JLabel("Username");
        usernameTextField = new JTextField();
        usernameTextField.setPreferredSize(new Dimension(100, 25));
        passwordLabel = new JLabel("Password");
        passwordTextField = new JTextField();
        passwordTextField.setPreferredSize(new Dimension(100, 25));
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 60));
        uiPanel = new JPanel();
        uiPanel.setPreferredSize(new Dimension(450, 750));
        usernamePanel = new JPanel();
        usernamePanel.setPreferredSize(new Dimension(400, 300));
        passwordPanel = new JPanel();
        passwordPanel.setPreferredSize(new Dimension(400, 300));

        usernamePanel.add(usernameLabel, BorderLayout.EAST);
        usernamePanel.add(usernameTextField, BorderLayout.WEST);

        passwordPanel.add(passwordLabel, BorderLayout.EAST);
        passwordPanel.add(passwordTextField, BorderLayout.WEST);

//        GridLayout gridLayout = new GridLayout(3, 1);
//        uiPanel.setLayout(gridLayout);
        uiPanel.add(usernamePanel, BorderLayout.CENTER);
        uiPanel.add(passwordPanel, BorderLayout.CENTER);
        uiPanel.add(loginButton, BorderLayout.CENTER);

        add(uiPanel, BorderLayout.CENTER);
    }

    public JPanel getUiPanel() {
        return uiPanel;
    }

    public void setUiPanel(JPanel uiPanel) {
        this.uiPanel = uiPanel;
    }

    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    public void setUsernameLabel(JLabel usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public void setPasswordLabel(JLabel passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    public void setUsernameTextField(JTextField usernameTextField) {
        this.usernameTextField = usernameTextField;
    }

    public JTextField getPasswordTextField() {
        return passwordTextField;
    }

    public void setPasswordTextField(JTextField passwordTextField) {
        this.passwordTextField = passwordTextField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(JButton loginButton) {
        this.loginButton = loginButton;
    }

    public JPanel getUsernamePanel() {
        return usernamePanel;
    }

    public void setUsernamePanel(JPanel usernamePanel) {
        this.usernamePanel = usernamePanel;
    }

    public JPanel getPasswordPanel() {
        return passwordPanel;
    }

    public void setPasswordPanel(JPanel passwordPanel) {
        this.passwordPanel = passwordPanel;
    }
}
