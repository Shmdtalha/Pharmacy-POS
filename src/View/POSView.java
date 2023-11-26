package View;

import Model.Entity.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class POSView extends BaseView {

    private JTable itemsTable;
    private DefaultTableModel itemsModel;
    private JTextField searchField;
    private JLabel totalLabel;
    private JButton clearButton, removeItemButton, generateInvoiceButton;
    private JPanel bottomPanel;

    public POSView(String title) {
        super(title);
    }

    @Override
    protected void initializeComponents() {
        setLayout(new BorderLayout());

        // Table setup
        String[] columnNames = {"Product Code", "Product Name", "Price", "Quantity", "Total Price"};
        itemsModel = new DefaultTableModel(columnNames, 0);
        itemsTable = new JTable(itemsModel);
        JScrollPane tableScrollPane = new JScrollPane(itemsTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // Search bar setup
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchField = new JTextField();
        searchPanel.add(searchField, BorderLayout.CENTER);
        add(searchPanel, BorderLayout.NORTH);

        // Bottom panel setup
        bottomPanel = new JPanel();
        clearButton = new JButton("Clear All");
        removeItemButton = new JButton("Remove Item");
        generateInvoiceButton = new JButton("Generate Invoice");
        totalLabel = new JLabel("Total: $0.00");

        bottomPanel.add(clearButton);
        bottomPanel.add(removeItemButton);
        bottomPanel.add(generateInvoiceButton);
        bottomPanel.add(totalLabel);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public JTable getItemsTable() {
        return itemsTable;
    }

    public DefaultTableModel getItemsModel() {
        return itemsModel;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JLabel getTotalLabel() {
        return totalLabel;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JButton getRemoveItemButton() {
        return removeItemButton;
    }

    public JButton getGenerateInvoiceButton() {
        return generateInvoiceButton;
    }
}
