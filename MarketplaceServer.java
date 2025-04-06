package server;
import services.*; //imports our other service classes

/**
 * This is the MarketplaceServer class.
 *
 * <p>Purdue University -- CS18000 -- Spring 2025 -- Team Project
 *
 * @author Jay Saini Purdue CS
 * @version April 6, 2025
 */



public class MarketplaceServer implements IMarketplaceServer {
    public UserService users;
    public ProductService products;
    public TransactionService transactions;
    public MessageService messages;

    public MarketplaceServer(UserService u, ProductService p, TransactionService t, MessageService m) {
        this.users = u;
        this.products = p;
        this.transactions = t;
        this.messages = m;
    }

  
    @Override
    public boolean registerUser(String username, String email, String password) {
        return users.register(username, email, password);
    }

    @Override
    public boolean loginUser(String username, String password) {
        return users.login(username, password);
    }


    
    @Override
    public boolean addProduct(String name, double price, String description, int sellerId) {
        return products.addProduct(name, price, description, sellerId);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return products.searchProducts(keyword);
    }

    @Override
    public boolean createTransaction(int buyerId, int productId) {
        return transactions.processTransaction(buyerId, productId);
    }

    @Override
    public boolean sendMessage(int senderId, int receiverId, String content) {
        return messages.sendMessage(senderId, receiverId, content);
    }
}
