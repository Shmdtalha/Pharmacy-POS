package Service;

import Model.DAO.ProductDAO;
import Model.Entity.CustomerCart;
import Model.Entity.Item;
import Model.Entity.Product;
import View.POSView;
import View.BaseView;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class POSService extends BaseService {
    private POSView posView;
    private ProductDAO productDAO;
    private CustomerCart customerCart;

    public POSService(BaseView view) {
        super(view);
        this.productDAO = new ProductDAO();
        customerCart = new CustomerCart();
    }

    @Override
    public void loadDialogBoxes() {

    }

    @Override
    protected void setChildReference() {
        if (view instanceof POSView) {
            posView = (POSView) view;
        } else {
            throw new RuntimeException("View must be an instance of POSView");
        }
    }

    @Override
    protected void checkViewType() {

    }

    @Override
    protected void addListeners() {

        posView.getSearchField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = posView.getSearchField().getText();
                if (!searchText.trim().isEmpty()) {
                    // Search and update the search results dropdown
                    List<Product> searchResults = productDAO.searchProductsByName(searchText);
                    updateSearchResultsDropdown(searchResults);
                } else {
                    posView.getSearchResultsDropdown().setModel(new DefaultComboBoxModel<>());
                }
            }
        });

        posView.getItemsModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (row >= 0 && column >= 0) {
                    SwingUtilities.invokeLater(() -> {
                        updateRowTotal(row);
                        updateTotalLabel();
                    });
                }
            }
        });

        posView.getItemsModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (row >= 0 && (column == 3 || column == 2)) { // Check for quantity or price column
                    SwingUtilities.invokeLater(() -> {
                        try {
                            Object value = posView.getItemsModel().getValueAt(row, column);
                            if (value != null) {
                                if (column == 3) { // Quantity column
                                    int quantity = Integer.parseInt(value.toString());
                                    if (quantity < 0) {
                                        JOptionPane.showMessageDialog(posView, "Quantity cannot be negative.");
                                        posView.getItemsModel().setValueAt(1, row, column); // Reset to default
                                        return;
                                    }
                                } else if (column == 2) { // Price column
                                    double price = Double.parseDouble(value.toString());
                                    if (price < 0.0) {
                                        JOptionPane.showMessageDialog(posView, "Price cannot be negative.");
                                        posView.getItemsModel().setValueAt(0.0, row, column); // Reset to default
                                        return;
                                    }
                                }
                                updateRowTotal(row);
                                updateTotalLabel();
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(posView, "Please enter a valid number.");
                            posView.getItemsModel().setValueAt(column == 3 ? 1 : 0.0, row, column); // Reset to default
                        }
                    });
                }
            }
        });


        posView.getAddButton().addActionListener(e -> {
            Product selectedProduct = (Product) posView.getSearchResultsDropdown().getSelectedItem();
            if (selectedProduct != null) {
                addProductToTable(selectedProduct);
            } else {
                JOptionPane.showMessageDialog(posView, "Please select a product to add.");
            }
        });

        posView.getRemoveItemButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = posView.getItemsTable().getSelectedRow();
                if (selectedRow >= 0) {

                    int confirm = JOptionPane.showConfirmDialog(
                            posView,
                            "Are you sure you want to remove the selected item?",
                            "Remove Item",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        ((DefaultTableModel) posView.getItemsTable().getModel()).removeRow(selectedRow);
                        updateTotalLabel();
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            posView,
                            "Please select an item to remove.",
                            "No Item Selected",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });


        posView.getGenerateInvoiceButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = posView.getItemsModel();
                int totalItems = model.getRowCount();
                double cartPrice = 0;

                for(int i = 0; i < totalItems; i++){
                    String productCode = (String) model.getValueAt(i, 0);
                    String productName = (String) model.getValueAt(i, 1);
                    double productPrice = (Double) model.getValueAt(i, 2);
                    int quantity = (Integer) model.getValueAt(i, 3);
                    double totalPrice = (Double) model.getValueAt(i, 4);
                    Item rowItem = new Item(quantity, productPrice, totalPrice, productCode, productName);
                    customerCart.add(rowItem);
                    cartPrice+=totalPrice;
                }
                customerCart.setTotalAmount(cartPrice);
                customerCart.generateOrder();
                customerCart.clear();

                refreshView();

            }
        });

        posView.getClearButton().addActionListener(e-> {
            refreshView();
        });

    }

    @Override
    public void refreshView() {
        posView.getSearchField().setText("");
        posView.getItemsModel().setRowCount(0);
        updateTotalLabel();
    }

    private void updateSearchResultsDropdown(List<Product> searchResults) {
        DefaultComboBoxModel<Product> model = (DefaultComboBoxModel<Product>) posView.getSearchResultsDropdown().getModel();
        model.removeAllElements();
        for (Product product : searchResults) {
            model.addElement(product);
        }
    }

    private void updateRowTotal(int row) {
        DefaultTableModel model = (DefaultTableModel) posView.getItemsTable().getModel();
        // Convert the string to integer
        int quantity;
        double price;
        try {
            quantity = Integer.parseInt(model.getValueAt(row, 3).toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(posView, "Please enter a valid number for quantity.");
            return;
        }

        try {
            price = Double.parseDouble(model.getValueAt(row, 2).toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(posView, "Please enter a valid number for price.");
            return;
        }

        double total = quantity * price;
        model.setValueAt(total, row, 4); // Total price column
    }


    private void updateTotalLabel() {
        double total = 0.0;
        for (int i = 0; i < posView.getItemsModel().getRowCount(); i++) {
            total += (Double) posView.getItemsModel().getValueAt(i, 4);
        }

        posView.getTotalLabel().setText("Total: $" + String.format("%.2f", total));
    }

    private void addProductToTable(Product product) {
        DefaultTableModel model = (DefaultTableModel) posView.getItemsTable().getModel();
        Object[] row = new Object[] {
                product.getCode(),
                product.getName(),
                product.getPrice(),
                1,
                product.getPrice()
        };
        model.addRow(row);
        updateTotalLabel();
    }
}
