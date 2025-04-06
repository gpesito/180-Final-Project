package server;

import java.util.List;
import models.Product;

public interface IMarketplaceServer {
    boolean registerUser(String username, String email, String password);
    boolean loginUser(String username, String password);
    boolean addProduct(String name, double price, String description, int sellerId);
    List<Product> searchProducts(String keyword);
    boolean createTransaction(int buyerId, int productId);
    boolean sendMessage(int senderId, int receiverId, String content);
}
