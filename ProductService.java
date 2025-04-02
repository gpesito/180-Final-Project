package com.marketplace.server;

import com.marketplace.models.Product;
import com.marketplace.interfaces.IProductService;

import java.util.HashMap;
import java.util.Map;

public class ProductService implements IProductService {
    private Map<String, Product> products;

    public ProductService() {
        products = new HashMap<>();
    }

    public void addProduct(String productID, String name, String description, double price, String sellerID, String category) {
        Product product = new Product(productID, name, description, price, sellerID, category);
        products.put(productID, product);
    }

    public void deleteProduct(String productID) {
        products.remove(productID);
    }

    public List<Product> searchProducts(String keyword) {
        return new ArrayList<>(products.values());  // For now, returning all products
    }

    public List<Product> searchByCategory(String category) {
        return new ArrayList<>(products.values());
    }
}
