package server;

import services.*;

public class MarketplaceServer {
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
}
