import java.io.Serializable;

/**
 * This class represents a product in an inventory
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 *
 * @author Milica Slavkovic
 * @version April 18, 2025
 */

public class Product implements IProduct, Serializable {
    private static final long serialVersionUID = 1L;

    private String productID; // Unique identifier for the product
    private String name; // Name of the product
    private String description; // Detailed description of the product
    private double price; // Price of the product
    private String category; // Category to which the product belongs
    private String sellerId; // The ID of the seller

    // Constructor to initialize all fields of the Product
    public Product(String productID, String name, String description, double price,
                   String category, String sellerId) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.sellerId = sellerId;
    }

    // Legacy constructor
    public Product(String productID, String name, String description, double price, String category) {
        this(productID, name, description, price, category, "");
    }

    // Getters and setters
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "Product{" +
               "productID='" + productID + '\'' +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", price=" + price +
               ", category='" + category + '\'' +
               ", sellerId='" + sellerId + '\'' +
               '}';
    }
}
