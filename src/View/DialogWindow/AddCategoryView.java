package View.DialogWindow;

import Model.Entity.Category;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AddCategoryView extends JDialog {

    private JComboBox<Category> parentCategoryDropdown;
    private JTextField categoryNameField;
    private JTextField categoryCodeField;
    private JTextField categoryDescriptionField;
    private JButton addButton;

    public AddCategoryView(Frame owner, boolean modal) {
        super(owner, modal);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        this.setLayout(new BorderLayout());
        this.setSize(500, 200);

        // Initializing components
        JLabel categoryNameLabel = new JLabel("Category Name:");
        categoryNameField = new JTextField(20);
        JLabel categoryCodeLabel = new JLabel("Category Code:");
        categoryCodeField = new JTextField(20);
        JLabel categoryDescriptionLabel = new JLabel("Description:");
        categoryDescriptionField = new JTextField(20);
        JLabel parentCategorylabel = new JLabel("Parent:");
        parentCategoryDropdown = new JComboBox<>();
        addButton = new JButton("Add");

        // Layout components
        this.add(parentCategoryDropdown, BorderLayout.NORTH);
        JPanel fieldsPanel = new JPanel(new GridLayout(0, 2)); // Use 0, 2 for GridLayout to have 2 columns

        // Labels and text fields
        fieldsPanel.add(categoryNameLabel);
        fieldsPanel.add(categoryNameField);
        fieldsPanel.add(categoryCodeLabel);
        fieldsPanel.add(categoryCodeField);
        fieldsPanel.add(categoryDescriptionLabel);
        fieldsPanel.add(categoryDescriptionField);
        fieldsPanel.add(parentCategorylabel);
        fieldsPanel.add(parentCategoryDropdown);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);

        this.add(fieldsPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

    }


    public JComboBox<Category> getParentCategoryDropdown() {
        return parentCategoryDropdown;
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

    public JButton getAddButton() {
        return addButton;
    }
}


