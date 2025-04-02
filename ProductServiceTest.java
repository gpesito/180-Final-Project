
package com.marketplace.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.marketplace.server.ProductService;
import com.marketplace.models.Product;
import java.util.List;

public class ProductServiceTest {
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService();
        productService.addProduct("P123", "Laptop", "High-end gaming laptop", 1200.99, "S001", "Electronics");
        productService.addProduct("P124", "Smartphone", "Latest model smartphone", 799.49, "S002", "Electronics");
    }

    @Test
    void testAddProduct() {
        productService.addProduct("P125", "Tablet", "Lightweight tablet", 499.99, "S003", "Electronics");
        List<Product> products = productService.searchProducts(""); // Returns all products
        assertEquals(3, products.size());
    }

    @Test
    void testDeleteProduct() {
        productService.deleteProduct("P123");
        List<Product> products = productService.searchProducts("");
        assertEquals(1, products.size());
    }

    @Test
    void testSearchByCategory() {
        List<Product> electronics = productService.searchByCategory("Electronics");
        assertEquals(2, electronics.size());
    }
}
