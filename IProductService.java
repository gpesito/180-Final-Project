package com.marketplace.interfaces;

import java.util.List;

public interface IProductService {
    void addProduct(String productID, String name, String description, double price, String sellerID, String category);
    void deleteProduct(String productID);
    List<Product> searchProducts(String keyword);
    List<Product> searchByCategory(String category);
}
