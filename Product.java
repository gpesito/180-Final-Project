// CS180 Product class - Milica

public class Product {
    private String productID;
    private String name;
    private String description;
    private double price;
    private String category;

    public Product(String productID, String name, String description, double price, String category) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
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
}
