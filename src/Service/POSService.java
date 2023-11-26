package Service;

import Model.DAO.ProductDAO;
import Model.Entity.Cart;
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
    private Cart cart;

    public POSService(BaseView view) {
        super(view);
        this.productDAO = new ProductDAO();
        cart = new Cart();
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


        posView.getAddButton().addActionListener(e -> {
            Product selectedProduct = (Product) posView.getSearchResultsDropdown().getSelectedItem();
            if (selectedProduct != null) {
                addProductToTable(selectedProduct);
            } else {
                JOptionPane.showMessageDialog(posView, "Please select a product to add.");
            }
        });

        posView.getGenerateInvoiceButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cart cart = new Cart();

            }
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
