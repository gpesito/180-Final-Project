package server;

import services.UserService;
import services.ProductService;
import services.TransactionService;
import services.MessageService;

import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * This is the MarketplaceServer class.
 *
 * <p>Purdue University -- CS18000 -- Spring 2025 -- Team Project
 *
 * @author Jay Saini Purdue CS
 * @version April 30, 2025
 */

public class MarketplaceServer implements IMarketplaceServer, Runnable {
    private final UserService users;
    private final ProductService products;
    private final TransactionService transactions;
    private final MessageService messages;
    private final Socket clientSocket;

    //connects with other classes
    public MarketplaceServer(Socket socket, UserService u, ProductService p, TransactionService t, MessageService m) {
        this.clientSocket = socket;
        this.users = u;
        this.products = p;
        this.transactions = t;
        this.messages = m;
    }

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
                String[] tokens = inputLine.trim().split(" ");
                String command = tokens[0].toUpperCase();

                try {
                    switch (command) {
                        case "REGISTER":
                            if (tokens.length < 4) {
                                out.println("FAILURE|REGISTER requires username, email, and password.");
                            } else {
                                boolean result = registerUser(tokens[1], tokens[2], tokens[3]);
                                out.println(result ? "SUCCESS|User registered." : "FAILURE|Username already exists.");
                            }
                            break;

                        case "LOGIN":
                            if (tokens.length < 3) {
                                out.println("FAILURE|LOGIN requires username and password.");
                            } else {
                                boolean result = loginUser(tokens[1], tokens[2]);
                                out.println(result ? "SUCCESS|Login successful." : "FAILURE|Invalid credentials.");
                            }
                            break;

                        case "ADD":
                            if (tokens.length < 5) {
                                out.println("FAILURE|ADD requires name, price, description, and sellerId.");
                            } else {
                                String name = tokens[1];
                                double price = Double.parseDouble(tokens[2]);
                                String desc = tokens[3];
                                int sellerId = Integer.parseInt(tokens[4]);

                                boolean result = addProduct(name, price, desc, sellerId);
                                out.println(result ? "SUCCESS|Product added." : "FAILURE|Could not add product.");
                            }
                            break;

                        case "SEARCH":
                            if (tokens.length < 2) {
                                out.println("FAILURE|SEARCH requires a keyword.");
                            } else {
                                String keyword = tokens[1];
                                List<Product> results = searchProducts(keyword);
                                if (results.isEmpty()) {
                                    out.println("DATA|No products found.");
                                } else {
                                    for (Product p : results) {
                                        out.println("DATA|" + p.toString());
                                    }
                                }
                            }
                            break;

                        case "TRANSACT":
                            if (tokens.length < 3) {
                                out.println("FAILURE|TRANSACT requires buyerId and productId.");
                            } else {
                                int buyerId = Integer.parseInt(tokens[1]);
                                int productId = Integer.parseInt(tokens[2]);

                                boolean result = createTransaction(buyerId, productId);
                                out.println(result ? "SUCCESS|Transaction completed." : "FAILURE|Transaction failed.");
                            }
                            break;

                        case "MESSAGE":
                            if (tokens.length < 4) {
                                out.println("FAILURE|MESSAGE requires senderId, receiverId, and content.");
                            } else {
                                int senderId = Integer.parseInt(tokens[1]);
                                int receiverId = Integer.parseInt(tokens[2]);
                                String content = tokens[3];

                                boolean result = sendMessage(senderId, receiverId, content);
                                out.println(result ? "SUCCESS|Message sent." : "FAILURE|Message failed.");
                            }
                            break;

                        case "EXIT":
                            out.println("SUCCESS|Goodbye!");
                            return;

                        default:
                            out.println("FAILURE|Unknown command.");
                    }
                } catch (NumberFormatException e) {
                    out.println("FAILURE|Invalid number format: " + e.getMessage());
                } catch (Exception e) {
                    out.println("FAILURE|Server error: " + e.getMessage());
                    e.printStackTrace();
                }

                out.println();
            }
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        }
    }
}
