/**
 * Unit tests for the Product class to verify that getters return the expected values
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 *
 * @author Milica Slavkovic
 * @version April 6, 2025
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest {

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product("1", "Laptop", "A powerful laptop", 1000.00, "Electronics");
    }

    @Test
    public void testGetProductID() {
        assertEquals("1", product.getProductID());
    }

    @Test
    public void testGetName() {
        assertEquals("Laptop", product.getName());
    }

    @Test
    public void testGetDescription() {
        assertEquals("A powerful laptop", product.getDescription());
    }

    @Test
    public void testGetPrice() {
        assertEquals(1000.00, product.getPrice());
    }

    @Test
    public void testGetCategory() {
        assertEquals("Electronics", product.getCategory());
    }
}
