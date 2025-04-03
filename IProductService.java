import java.util.List;

public interface IProductService {
    void addProduct(String productID, String name, String description, double price, String category);
    void deleteProduct(String productID);
    List<IProduct> searchByCategory(String category);
    List<IProduct> searchProducts(String keyword);
}

