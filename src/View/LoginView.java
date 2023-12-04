package View;

import javax.swing.*;
import java.awt.*;

/**
 * The LoginView class presents a user interface for logging into the system.
 * It includes fields for username and password and a login button.
 */
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

    /**
     * Initializes the User interface components
     * through this inherited method.
     */
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

        usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameTextField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordTextField);
        buttonPanel.add(loginButton);


        uiPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        uiPanel.add(Box.createVerticalStrut(100));

        uiPanel.add(usernamePanel, gbc);
        uiPanel.add(passwordPanel, gbc);
        gbc.weighty = 1;
        uiPanel.add(buttonPanel, gbc);

        add(uiPanel, BorderLayout.CENTER);
    }

    public void showError(String message){
        JOptionPane.showMessageDialog(uiPanel, message);
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
