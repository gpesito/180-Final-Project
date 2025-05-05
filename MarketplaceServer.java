package server;

import services.UserService;
import services.ProductService;
import services.TransactionService;
import services.MessageService;
import models.User;
import models.Product;

import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * This is the MarketplaceServer class.
 *
 * <p>Purdue University -- CS18000 -- Spring 2025 -- Team Project
 *
 * @author Jay Saini Purdue CS
 * @version May 4, 2025
 */

//Help handle user, product, transaction, and messages
public class MarketplaceServer implements IMarketplaceServer, Runnable {
    private final UserService users;
    private final ProductService products;
    private final TransactionService transactions;
    private final MessageService messages;
    private final Socket clientSocket;

    public MarketplaceServer(Socket socket, UserService u, ProductService p, TransactionService t, MessageService m) {
        this.clientSocket = socket;
        this.users = u;
        this.products = p; 
        this.transactions = t;
        this.messages = m;
    }

    //creates new user
    @Override
    public boolean registerUser(String username, String email, String password) {
        return users.registerUser(new User(username, email, password));
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

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String[] tokens = inputLine.split(" ", 2);
                String command = tokens[0].toUpperCase();

                switch (command) {
                    case "REGISTER": {
                        String[] args = tokens[1].split(" ");
                        out.println(registerUser(args[0], args[1], args[2]));
                        break;
                    }
                    case "LOGIN": {
                        String[] args = tokens[1].split(" ");
                        out.println(loginUser(args[0], args[1]));
                        break;
                    }
                    case "ADD": {
                        String[] args = tokens[1].split(" ", 4);
                        String name = args[0];
                        double price = Double.parseDouble(args[1]);
                        String description = args[2];
                        int sellerId = Integer.parseInt(args[3]);
                        out.println(addProduct(name, price, description, sellerId));
                        break;
                    }
                    case "SEARCH":
                        for (Product p : searchProducts(tokens[1])) {
                            out.println(p.toString());
                        }
                        break;
                    case "TRANSACT": {
                        String[] args = tokens[1].split(" ");
                        out.println(createTransaction(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
                        break;
                    }
                    case "MESSAGE": {
                        String[] args = tokens[1].split(" ", 3);
                        int senderId = Integer.parseInt(args[0]);
                        int receiverId = Integer.parseInt(args[1]);
                        String content = args[2];
                        out.println(sendMessage(senderId, receiverId, content));
                        break;
                    }
                    case "EXIT":
                        out.println("Bye!");
                        return;
                    default:
                        out.println("Unknown command.");
                }
                out.println(); // signal end of response
            }
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
