package Model.Entity;

import java.util.HashSet;
import java.util.Set;

public class Product {
    private final String code;
    private String name;
    private final String description;
    private int stockQuantity;
    private double price;

    String parentCategoryName;

    public String getCode() { return code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public int getStockQuantity() { return stockQuantity; }
    public double getPrice() { return price; }

    public void setPrice(double price) {
        this.price = price;
    }

    private final Set<Category> categories;
    public void addCategory(Category category) {
        categories.add(category);
        category.getProducts().add(this);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
        category.getProducts().remove(this);
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Product(String code, String name, String description, int stockQuantity, double price) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.stockQuantity = stockQuantity;
        this.price = price;
        categories = new HashSet<>();
    }

    public void setParentCategoryName(String parentCategoryName) {
        this.parentCategoryName = parentCategoryName;
    }

    public String getParentCategoryName() {
        return parentCategoryName;
    }

    @Override
    public String toString(){
        return code + "." + name + ": " + stockQuantity + " / $" + price;
    }
}
