import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ProductService {
    private Map<String, Product> products;

    public ProductService() {
        products = new HashMap<>();
    }

    // Add product
    public void addProduct(String productID, String name, String description, double price, String category) {
        Product product = new Product(productID, name, description, price, category);
        products.put(productID, product);
    }

    // Delete product
    public void deleteProduct(String productID) {
        products.remove(productID);
    }

    // Search products by category
    public List<Product> searchByCategory(String category) {
        List<Product> result = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getCategory().equals(category)) {
                result.add(product);
            }
        }
        return result;
    }

    // Search products by keyword
    public List<Product> searchProducts(String keyword) {
        List<Product> result = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getName().contains(keyword) || product.getDescription().contains(keyword)) {
                result.add(product);
            }
        }
        return result;
    }
}
