import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * This class managies a collection of products
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 *
 * @author Milica Slavkovic
 * @version April 6, 2025
 */

public class ProductService {
    private Map<String, Product> products; // A map to store products using productID as the key
    
// Constructor initializes the product map
    public ProductService() {
        products = new HashMap<>();
    }

// Add product
    public void addProduct(String productID, String name, String description, double price, String category) {
        Product product = new Product(productID, name, description, price, category);
        products.put(productID, product); // Store the product with productID as the key
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
                result.add(product); // Add matching products to the result list
            }
        }
        return result;
    }

// Search products by keyword
    public List<Product> searchProducts(String keyword) {
        List<Product> result = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getName().contains(keyword) || product.getDescription().contains(keyword)) {
                result.add(product); // Add products that match the keyword
            }
        }
        return result;
    }
}
