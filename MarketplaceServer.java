package server;
import services.*; //imports our other service classes
import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * This is the MarketplaceServer class.
 *
 * <p>Purdue University -- CS18000 -- Spring 2025 -- Team Project
 *
 * @author Jay Saini Purdue CS
 * @version April 20, 2025
 */

//Help handle user, product, transaction and messages
public class MarketplaceServer implements IMarketplaceServer, Runnable {
    public UserService users;
    public ProductService products;
    public TransactionService transactions;
    public MessageService messages;
    private Socket clientSocket;

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

    
    @Override
   
   
    public void run() {
        try (
                
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        ) {
          
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String[] tokens = inputLine.split(" ");
                String command = tokens[0].toUpperCase();

                switch (command) {
                    case "REGISTER":
                        out.println(registerUser(tokens[1], tokens[2], tokens[3]));
                        break;
                    case "LOGIN":
                        out.println(loginUser(tokens[1], tokens[2]));
                        break;
                    case "ADD":
                        out.println(addProduct(tokens[1], Double.parseDouble(tokens[2]), tokens[3], Integer.parseInt(tokens[4])));
                        break;
                    case "SEARCH":
                        for (Product p : searchProducts(tokens[1])) {
                            out.println(p.toString());
                        }
                        break;
                    case "TRANSACT":
                        out.println(createTransaction(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
                        break;
                    case "MESSAGE":
                        out.println(sendMessage(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), tokens[3]));
                        break;
                    case "EXIT":
                        out.println("Bye!");
                        return;
                    default:
                        out.println("Unknown command.");
                }
                out.println(); 
            }

        } catch (IOException | NumberFormatException e) {
            System.err.println("Error handling client: " + e.getMessage());
        }
    }
}
