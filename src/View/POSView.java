package View;

import Model.Entity.Product;
import Service.MainDashboardService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The POSView class represents the point of sale interface.
 * It includes features for searching products, adding items to the sale, and generating invoices.
 */
public class POSView extends BaseView {

    private JTable itemsTable;
    private DefaultTableModel itemsModel;
    private JTextField searchField;
    private JComboBox<Product> searchResultsDropdown;
    private DefaultComboBoxModel<Product> searchResultsModel;
    private JLabel totalLabel;
    private JButton addButton, clearButton, removeItemButton, generateInvoiceButton;

    /**
     * The POSView class represents the point of sale interface.
     * It includes features for searching products, adding items to the sale, and generating invoices.
     */
    public POSView(String title) {
        super(title);
    }

    /**
     * Initializes the User interface components
     * through this inherited method.
     */
    @Override
    protected void initializeComponents() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        itemsModel = new DefaultTableModel(new Object[]{"Product Code", "Product Name", "Price", "Quantity", "Total"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0: // Product Code
                        return String.class;
                    case 1: // Product Name
                        return String.class;
                    case 2: // Price
                        return Double.class;
                    case 3: // Quantity
                        return Integer.class;
                    case 4: // Total
                        return Double.class;
                    default:
                        return Object.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3 || column == 2; // Only quantity and price columns are editable
            }
        };


        itemsTable = new JTable(itemsModel);
        JScrollPane tableScrollPane = new JScrollPane(itemsTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // Search bar setup
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(20);
        searchResultsModel = new DefaultComboBoxModel<>();
        searchResultsDropdown = new JComboBox<>(searchResultsModel);
        searchResultsDropdown.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Product) {
                    setText(((Product) value).getName());
                }
                return this;
            }
        });
        searchPanel.add(searchField);
        searchPanel.add(searchResultsDropdown);
        addButton = new JButton("Add");
        searchPanel.add(addButton);

        // Bottom panel setup
        JPanel bottomPanel = new JPanel();
        clearButton = new JButton("Clear");
        removeItemButton = new JButton("Remove Item");
        generateInvoiceButton = new JButton("Generate Invoice");
        totalLabel = new JLabel("Total: $0.00");

        bottomPanel.add(clearButton);
        bottomPanel.add(removeItemButton);
        bottomPanel.add(generateInvoiceButton);
        bottomPanel.add(totalLabel);
        add(bottomPanel, BorderLayout.SOUTH);

        // Add the search panel below the table
        add(searchPanel, BorderLayout.NORTH);
    }

    public JComboBox<Product> getSearchResultsDropdown() {
        return searchResultsDropdown;
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

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getRemoveItemButton() {
        return removeItemButton;
    }

    public JButton getGenerateInvoiceButton() {
        return generateInvoiceButton;
    }
}
