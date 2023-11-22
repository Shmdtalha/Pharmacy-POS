package View;

import javax.swing.JFrame;

public abstract class BaseView extends JFrame {

    public BaseView(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        initializeComponents();
        setVisible(true);
    }

    protected abstract void initializeComponents();
    


}
