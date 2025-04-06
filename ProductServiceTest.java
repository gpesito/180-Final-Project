import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

/**
 * Unit tests for the ProductService class to verify adding, deleting, and searching products works as expected
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 *
 * @author Milica Slavkovic
 * @version April 6, 2025
 */

public class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    public void setUp() {
        productService = new ProductService();
        productService.addProduct("1", "Laptop", "A powerful laptop", 1000.00, "Electronics");
        productService.addProduct("2", "Smartphone", "A latest smartphone", 700.00, "Electronics");
    }

    @Test
    public void testAddProduct() {
        productService.addProduct("3", "Tablet", "A great tablet", 500.00, "Electronics");
        List<Product> products = productService.searchByCategory("Electronics");
        assertEquals(3, products.size());
    }

    @Test
    public void testDeleteProduct() {
        productService.deleteProduct("1");
        List<Product> products = productService.searchByCategory("Electronics");
        assertEquals(1, products.size());
    }

    @Test
    public void testSearchByCategory() {
        List<Product> products = productService.searchByCategory("Electronics");
        assertEquals(2, products.size());
    }

    @Test
    public void testSearchProducts() {
        List<Product> products = productService.searchProducts("Smartphone");
        assertEquals(1, products.size());
    }
}
