import java.util.List;

/**
 * Interface that defines the contract for ProductService class to manage products
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 *
 * @author Milica Slavkovic
 * @version April 6, 2025
 */

public interface IProductService {
    void addProduct(String productID, String name, String description, double price, String category);
    void deleteProduct(String productID);
    List<IProduct> searchByCategory(String category);
    List<IProduct> searchProducts(String keyword);
}

