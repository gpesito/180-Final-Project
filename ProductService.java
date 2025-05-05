import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Thread-safe implementation of the IProductService interface.
 * Manages a collection of products in memory.
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 * @author Milica Slavkovic
 * @version April 12, 2025
 */
public class ProductService implements IProductService {
    private final Map<String, IProduct> products;

    public ProductService() {
        products = new ConcurrentHashMap<>(); // Thread-safe map
    }

    // Add product
    @Override
    public synchronized void addProduct(String productID, String name, String description, double price, String category) {
        IProduct product = new Product(productID, name, description, price, category);
        products.put(productID, product);
    }

    // Delete product
    @Override
    public synchronized void deleteProduct(String productID) {
        products.remove(productID);
    }

    // Search products by category
    @Override
    public List searchByCategory(String category) {
        List<Product> result = new ArrayList<>();
        for (IProduct product : products.values()) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                result.add((Product) product); // Safe cast
            }
        }
        return result;
    }

    // Search products by keyword
    @Override
    public List searchProducts(String keyword) {
        List<Product> result = new ArrayList<>();
        for (IProduct product : products.values()) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                product.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add((Product) product); // Safe cast
            }
        }
        return result;
    }
}
