package Service;
import Model.DAO.CategoryDAO;
import Model.DAO.ProductDAO;
import Model.Entity.Category;
import Model.Entity.Product;
import View.BaseView;
import View.DialogWindow.ManageCategoryView;
import View.InventoryView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryService extends BaseService{
    InventoryView inventoryView;
    ManageCategoryView manageCategoryView;
    ProductDAO productDAO;
    CategoryDAO categoryDAO;

    public InventoryService(BaseView view)  {
        super(view);
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
        inventoryView.updateCategoryList(categoryDAO.loadAll());
        refreshTable();
    }

    @Override
    public void loadDialogBoxes(){
        manageCategoryView = new ManageCategoryView((Frame) SwingUtilities.getWindowAncestor(view), true);

    }

    @Override
    protected void setChildReference() {
        inventoryView=((InventoryView)view);
    }

    @Override
    protected void checkViewType() {
        try{
        if (!(view instanceof InventoryView)) {
            throw new Exception("Expected Inventory View");
        }}
        catch(Exception ex){

        }
    }

    @Override
    protected void addListeners() {
        System.out.println("Adding Listeners");
        inventoryView.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Adding add button action");
                List<Category> selectedCategories = inventoryView.getCategoryList().getSelectedValuesList();
                String code = inventoryView.getProductCodeField().getText();
                String name = inventoryView.getProductNameField().getText();
                String description = "This is a new product";
                int quantity;
                double price;

                try {
                    quantity = Integer.parseInt(inventoryView.getQuantityField().getText());
                    price = Double.parseDouble(inventoryView.getPriceField().getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(inventoryView, "Please enter valid numbers for quantity and price.");
                    return;
                }

                Product newProduct = new Product(code, name, description, quantity, price);
                System.out.println(newProduct);
                for (Category category : selectedCategories) {
                    newProduct.addCategory(category);
                    System.out.println(category.getName());
                }

                inventoryView.addProductToTable(code, name, quantity, price, selectedCategories.get(0).toString());
                productDAO.save(newProduct);
            }
        });

        inventoryView.getCategoryList().addListSelectionListener(e -> {
                refreshTable();
        });

        inventoryView.getManageCategoriesButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageCategoryView.getCategoryDropdown().removeAllItems();
                List<Category> categories = categoryDAO.loadAll();
                for(Category cat : categories){
                    manageCategoryView.getCategoryDropdown().addItem(cat);
                }
                manageCategoryView.setVisible(true);


            }
        });

        manageCategoryView.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Category selectedCategory = (Category) manageCategoryView.getCategoryDropdown().getSelectedItem();

                if (selectedCategory != null) {

                    int confirm = JOptionPane.showConfirmDialog(
                            manageCategoryView,
                            "Are you sure you want to delete the category: " + selectedCategory.getName() + "?",
                            "Delete Category",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        categoryDAO.delete(selectedCategory);
                        manageCategoryView.getCategoryDropdown().removeItem(selectedCategory);

                        manageCategoryView.getCategoryNameField().setText("");
                        manageCategoryView.getCategoryCodeField().setText("");
                        manageCategoryView.getCategoryDescriptionField().setText("");

                        JOptionPane.showMessageDialog(manageCategoryView, "Category deleted successfully.");

                    }
                } else {
                    JOptionPane.showMessageDialog(manageCategoryView, "Please select a category to delete.");
                }
            }
        });
        manageCategoryView.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Category selectedCategory = (Category) manageCategoryView.getCategoryDropdown().getSelectedItem();
                if (selectedCategory != null) {
                    String oldCode = selectedCategory.getCode();
                    // Determine which field is non-empty
                    if (!manageCategoryView.getCategoryNameField().getText().isEmpty()) {
                        selectedCategory.setName(manageCategoryView.getCategoryNameField().getText());
                    } else if (!manageCategoryView.getCategoryCodeField().getText().isEmpty()) {
                        selectedCategory.setCode(manageCategoryView.getCategoryCodeField().getText());
                    } else if (!manageCategoryView.getCategoryDescriptionField().getText().isEmpty()) {
                        selectedCategory.setDescription(manageCategoryView.getCategoryDescriptionField().getText());
                    }

                    // Now update the category in the database
                    categoryDAO.update(selectedCategory, oldCode);
                    JOptionPane.showMessageDialog(manageCategoryView, "Category updated successfully.");
                    refreshCategoryDropdown();

                    // Reset the editability of all fields
                    resetFieldEditability();
                } else {
                    JOptionPane.showMessageDialog(manageCategoryView, "Please select a category to update.");
                }
            }
        });


    }

    @Override
    public void refreshView() {
        inventoryView.getProductCodeField().setText("");
        inventoryView.getProductNameField().setText("");
        inventoryView.getQuantityField().setText("");
        inventoryView.getPriceField().setText("");
        inventoryView.getCategoryList().clearSelection();
    }

    private void resetFieldEditability() {
        manageCategoryView.getCategoryNameField().setEditable(true);
        manageCategoryView.getCategoryCodeField().setEditable(true);
        manageCategoryView.getCategoryDescriptionField().setEditable(true);
        manageCategoryView.getCategoryNameField().setEnabled(true);
        manageCategoryView.getCategoryCodeField().setEnabled(true);
        manageCategoryView.getCategoryDescriptionField().setEnabled(true);
        manageCategoryView.getCategoryNameField().setText("");
        manageCategoryView.getCategoryCodeField().setText("");
        manageCategoryView.getCategoryDescriptionField().setText("");
    }

    private void refreshCategoryDropdown() {
        manageCategoryView.getCategoryDropdown().removeAllItems();
        List<Category> categories = categoryDAO.loadAll();
        for (Category cat : categories) {
            manageCategoryView.getCategoryDropdown().addItem(cat);
        }
    }
    void refreshTable(){
        inventoryView.getInventoryModel().setRowCount(0);
        List<Product> selectedProducts = productDAO.getProductsByCategories(inventoryView.getCategoryList().getSelectedValuesList());
        System.out.println("Found " + selectedProducts.size() + " products");
        for(Product p : selectedProducts){
            inventoryView.addProductToTable(p.getCode(), p.getName(), p.getStockQuantity(), p.getPrice(), p.getCategories().stream()
                    .map(Category::getName)
                    .collect(Collectors.joining(", ")));
        }

    }

}
