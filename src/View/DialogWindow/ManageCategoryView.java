package View.DialogWindow;

import Model.Entity.Category;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ManageCategoryView extends JDialog {

    private JComboBox<Category> categoryDropdown;
    private JTextField categoryNameField;
    private JTextField categoryCodeField;
    private JTextField categoryDescriptionField;
    private JButton updateButton;
    private JButton deleteButton;

    public ManageCategoryView(Frame owner, boolean modal) {
        super(owner, modal);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        this.setLayout(new BorderLayout());
        this.setSize(500, 200);

        // Initializing components
        categoryDropdown = new JComboBox<>();
        JLabel categoryNameLabel = new JLabel("Category Name:");
        categoryNameField = new JTextField(20);
        JLabel categoryCodeLabel = new JLabel("Category Code:");
        categoryCodeField = new JTextField(20);
        JLabel categoryDescriptionLabel = new JLabel("Description:");
        categoryDescriptionField = new JTextField(20);
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");

        // Layout components
        this.add(categoryDropdown, BorderLayout.NORTH);
        JPanel fieldsPanel = new JPanel(new GridLayout(0, 2)); // Use 0, 2 for GridLayout to have 2 columns

        // Labels and text fields
        fieldsPanel.add(categoryNameLabel);
        fieldsPanel.add(categoryNameField);
        fieldsPanel.add(categoryCodeLabel);
        fieldsPanel.add(categoryCodeField);
        fieldsPanel.add(categoryDescriptionLabel);
        fieldsPanel.add(categoryDescriptionField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        this.add(fieldsPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        //Limits to only 1 text field
        categoryNameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                categoryCodeField.setEnabled(false);
                categoryDescriptionField.setEnabled(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(categoryNameField.getText().isEmpty()) {
                    categoryCodeField.setEnabled(true);
                    categoryDescriptionField.setEnabled(true);
                }
            }
        });

        categoryCodeField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                categoryNameField.setEnabled(false);
                categoryDescriptionField.setEnabled(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(categoryCodeField.getText().isEmpty()) {
                    categoryNameField.setEnabled(true);
                    categoryDescriptionField.setEnabled(true);
                }
            }
        });

        categoryDescriptionField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                categoryCodeField.setEnabled(false);
                categoryNameField.setEnabled(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(categoryDescriptionField.getText().isEmpty()) {
                    categoryCodeField.setEnabled(true);
                    categoryNameField.setEnabled(true);
                }
            }
        });
    }


    public JComboBox<Category> getCategoryDropdown() {
        return categoryDropdown;
    }

    public JTextField getCategoryNameField() {
        return categoryNameField;
    }

    public JTextField getCategoryCodeField() {
        return categoryCodeField;
    }

    public JTextField getCategoryDescriptionField() {
        return categoryDescriptionField;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

}


