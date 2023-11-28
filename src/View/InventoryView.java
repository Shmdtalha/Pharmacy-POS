package View;

import Model.Entity.Category;
import Model.Entity.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class InventoryView extends BaseView {

    private JTable inventoryTable;

    private DefaultTableModel inventoryModel;
    private JButton addButton, removeButton, updateButton, submitButton, addCategoryButton, manageCategoriesButton;
    private JTextField productcodefield, productNameField, quantityField, priceField;
    private JLabel productCodeLabel, productNameLabel, quantityLabel, priceLabel;

    JList<Category> categoryList;
    private DefaultListModel<Category> categoryListModel;
    private JScrollPane categoryScrollPane;

    public InventoryView(String title) {
        super(title);

    }


    @Override
    protected void initializeComponents() {
        setLayout(new BorderLayout());
        String[] columnNames = {"Product Code", "Product Name", "Quantity", "Price", "Category"};
        inventoryTable = new JTable();
        inventoryModel = new DefaultTableModel(columnNames, 0);
        inventoryTable.setModel(inventoryModel);
        JScrollPane scrollPane = new JScrollPane(inventoryTable);
        add(scrollPane, BorderLayout.CENTER);

        //Initializes category List and its model
        categoryListModel= new DefaultListModel<>();
        ArrayList<Category> categoryData = new ArrayList<>();

        updateCategoryList(categoryData);
        categoryList = new JList<>(categoryListModel);
        categoryList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // Allows multiple selections
        categoryScrollPane = new JScrollPane(categoryList);
        categoryScrollPane.setPreferredSize(new Dimension(categoryScrollPane.getPreferredSize().width * 2, categoryScrollPane.getPreferredSize().height));
        add(categoryScrollPane, BorderLayout.WEST);

        //Input panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10)); // 5 rows, 2 columns, 10px gaps
        productCodeLabel = new JLabel("Product Code:");
        productcodefield = new JTextField();
        productNameLabel = new JLabel("Product Name:");
        productNameField = new JTextField();
        quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField();
        priceLabel = new JLabel("Price:");
        priceField = new JTextField();

       //Adding components to input panel
        inputPanel.add(productCodeLabel);
        inputPanel.add(productcodefield);
        inputPanel.add(productNameLabel);
        inputPanel.add(productNameField);
        inputPanel.add(quantityLabel);
        inputPanel.add(quantityField);
        inputPanel.add(priceLabel);
        inputPanel.add(priceField);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        updateButton = new JButton("Update");
        submitButton = new JButton("Submit");
        addCategoryButton = new JButton("Add Category");
        manageCategoriesButton = new JButton("Manage Categories");

        //Adding components to Button Panel
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(submitButton);
        buttonPanel.add(addCategoryButton);
        buttonPanel.add(manageCategoriesButton);

        // Adding panels to the main frame
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);}

    public JTable getInventoryTable() {
        return inventoryTable;
    }
    public DefaultTableModel getInventoryModel() {
        return inventoryModel;
    }

    public JTextField getProductCodeField() { return productcodefield; }
    public JTextField getProductNameField() { return productNameField; }
    public JTextField getQuantityField() { return quantityField; }
    public JTextField getPriceField() { return priceField; }
    public JButton getAddButton() { return addButton; }
    public JButton getRemoveButton() { return removeButton; }
    public JButton getUpdateButton() { return updateButton; }
    public JButton getSubmitButton() { return submitButton; }

    public JButton getManageCategoriesButton(){return manageCategoriesButton;}

    public JList<Category> getCategoryList() {
        return categoryList;
    }

    public void updateCategoryList(java.util.List<Category> categories) {
        SwingUtilities.invokeLater(() -> {
            categoryListModel.clear();
            for (Category category : categories) {
                categoryListModel.addElement(category);
                for (Category subcategory : category.getSubcategories()) {
                    categoryListModel.addElement(subcategory);
                }
            }
        });
        }
    public void addProductToTable(String code, String name, int quantity, double price, String category) {
        Object[] rowData = new Object[] {code, name, quantity, price, category};
        inventoryModel.addRow(rowData);
    }

    public JButton getAddCategoryButton() {
        return addCategoryButton;
    }
}

