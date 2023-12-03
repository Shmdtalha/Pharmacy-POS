package Model.Entity;

import java.util.HashSet;
import java.util.Set;

public class Category {
    private String code;
    private String name;
    private String description;
    private final Set<Product> products;
    private final Set<Category> subcategories;
    private Category parentCategory;


    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Category(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
        products = new HashSet<>();
        subcategories = new HashSet<>();
    }


    public Category getParentCategory() { return parentCategory; }
    public void setParentCategory(Category parentCategory) {

        if (this.parentCategory != null) {
            this.parentCategory.getSubcategories().remove(this);
        }

        this.parentCategory = parentCategory;

        if (parentCategory != null) {
            parentCategory.getSubcategories().add(this);
        }
    }
    public Set<Category> getSubcategories() { return subcategories; }
    public Set<Product> getProducts() { return products; }

    public void addSubcategory(Category subcategory) {
        subcategories.add(subcategory);
        subcategory.setParentCategory(this);
    }
    public void removeSubcategory(Category subcategory) {
        subcategories.remove(subcategory);
        subcategory.setParentCategory(null);
    }
    public void addProduct(Product product) {
        products.add(product);
        product.getCategories().add(this);
    }
    public void removeProduct(Product product) {
        products.remove(product);
        product.getCategories().remove(this);
    }

    @Override
    public String toString(){
        return code+"." + name;
    }
}
