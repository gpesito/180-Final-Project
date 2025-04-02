package com.marketplace.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.marketplace.models.Product;

public class ProductTest{
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product("P123", "Laptop", "Gaming Laptop", 1299.99, "S456", "Electronics");
    }

    @Test
    void testProductInitialization() {
        assertEquals("P123", product.getProductID());
        assertEquals("Laptop", product.getName());
        assertEquals("Gaming Laptop", product.getDescription());
        assertEquals(1299.99, product.getPrice(), 0.001);
        assertEquals("S456", product.getSellerID());
        assertEquals("Electronics", product.getCategory());
        assertEquals("", product.getImageURL());
    }

    @Test
    void testUploadImage() {
        product.uploadImage("http://example.com/image.jpg");
        assertEquals("http://example.com/image.jpg", product.getImageURL());
    }

    @Test
    void testCreateListing() {
        // Just verifying that the method exists, as it prints output
        assertDoesNotThrow(() -> product.createListing());
    }

    @Test
    void testDeleteListing() {
        assertDoesNotThrow(() -> product.deleteListing());
    }

    @Test
    void testUpdateListing() {
        assertDoesNotThrow(() -> product.updateListing());
    }
}