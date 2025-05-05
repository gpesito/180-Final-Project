//package server;

import java.util.List;

/**
 * This is the interface for MarketplaceServer.java
 *
 * <p>Purdue University -- CS18000 -- Spring 2025 -- Team Project
 *
 * @author Jay Saini Purdue CS
 * @version April 30, 2025
 */


public interface IMarketplaceServer {
    boolean registerUser(String userId, String username, String email, String password);
    boolean loginUser(String username, String password);
    Object addProduct(String productID, String name, String description, double price, String category);
    List<Product> searchProducts(String keyword);
    boolean createTransaction(User buyer, User seller, double amount);
    boolean sendMessage(String senderId, String receiverId, String content, String productID);

    //this helps for socket handling
    void run(); 
}
