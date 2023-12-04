package View;

import javax.swing.JFrame;


public abstract class BaseView extends JFrame {

    /**
     * Calls the important functions that all child views require
     * @param title The title of the view
     */
    public BaseView(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        initializeComponents();
        setVisible(true);
    }

    /**
     * Initializes the User interface components
     * in all childs classes.
     */
    protected abstract void initializeComponents();
    


}
