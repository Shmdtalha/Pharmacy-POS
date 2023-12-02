package View.DialogWindow;

import Model.Entity.Product; // Assuming you have a Product entity

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class ManageExpiryView extends JDialog {

    private JComboBox<Product> productDropdown;
    private JFormattedTextField expiryDateField;
    private JTextField batchNumberField;
    private JTextField locationField;
    private JButton updateButton;
    private JButton deleteButton;
    private JList<String> upcomingExpiryList;
    private JList<String> allExpiryList;


    public ManageExpiryView(Frame owner, boolean modal) {
        super(owner, modal);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        this.setLayout(new BorderLayout());
        this.setSize(700, 500);

        // Initializing components
        productDropdown = new JComboBox<>();
        expiryDateField = new JFormattedTextField(new SimpleDateFormat("dd-MM-yyyy"));
        expiryDateField.setColumns(10);
        batchNumberField = new JTextField(20);
        locationField = new JTextField(20);
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        upcomingExpiryList = new JList<>();
        allExpiryList = new JList<>();

        // Layout components
        JPanel fieldsPanel = new JPanel(new GridLayout(0, 2));

        fieldsPanel.add(new JLabel("Select Product:"));
        fieldsPanel.add(productDropdown);
        fieldsPanel.add(new JLabel("Expiry Date:"));
        fieldsPanel.add(expiryDateField);
        fieldsPanel.add(new JLabel("Batch Number:"));
        fieldsPanel.add(batchNumberField);
        fieldsPanel.add(new JLabel("Location:"));
        fieldsPanel.add(locationField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        JPanel upcomingExpiryPanel = new JPanel(new BorderLayout());
        upcomingExpiryPanel.setBorder(BorderFactory.createTitledBorder("Upcoming Expiries"));
        upcomingExpiryPanel.add(new JScrollPane(upcomingExpiryList), BorderLayout.CENTER);

        JPanel allExpiryPanel = new JPanel(new BorderLayout());
        allExpiryPanel.setBorder(BorderFactory.createTitledBorder("All Expiries"));
        allExpiryPanel.add(new JScrollPane(allExpiryList), BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upcomingExpiryPanel, allExpiryPanel);
        splitPane.setResizeWeight(0.5);

        this.add(fieldsPanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.add(splitPane, BorderLayout.CENTER);
    }


    public JComboBox<Product> getProductDropdown() {
        return productDropdown;
    }

    public JFormattedTextField getExpiryDateField() {
        return expiryDateField;
    }

    public JTextField getBatchNumberField() {
        return batchNumberField;
    }

    public JTextField getLocationField() {
        return locationField;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JList<String> getUpcomingExpiryList() {
        return upcomingExpiryList;
    }
    public JList<String> getAllExpiryList() {
        return allExpiryList;
    }

}
