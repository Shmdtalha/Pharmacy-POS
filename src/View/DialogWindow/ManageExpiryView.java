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
    private JList<String> upcomingExpiryList; // JList for displaying upcoming expiries

    public ManageExpiryView(Frame owner, boolean modal) {
        super(owner, modal);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        this.setLayout(new BorderLayout());
        this.setSize(500, 400); // Increased size to accommodate the new list

        // Initializing components
        productDropdown = new JComboBox<>();
        expiryDateField = new JFormattedTextField(new SimpleDateFormat("dd-MM-yyyy"));
        expiryDateField.setColumns(10);
        batchNumberField = new JTextField(20);
        locationField = new JTextField(20);
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        upcomingExpiryList = new JList<>();

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

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder("Upcoming Expiries"));
        listPanel.add(new JScrollPane(upcomingExpiryList), BorderLayout.CENTER);

        this.add(fieldsPanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);
        this.add(listPanel, BorderLayout.SOUTH);
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
}
