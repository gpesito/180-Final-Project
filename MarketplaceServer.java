/**
    package server;

    import services.UserService;
    import services.ProductService;
    import services.TransactionService;
    import services.MessageService;
    import models.User;
    import models.Product;


 */
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
    public boolean registerUser(String userId, String username, String email, String password) {
        return users.registerUser(new User(userId, username, email, password));
    }

    @Override
    public boolean loginUser(String username, String password) {
        return users.login(username, password);
    }

    @Override
    public Object addProduct(String productID, String name, String description, double price, String category) {
        //String productID, String name, String description, double price, String category
        try {
            products.addProduct(productID, name, description, price, category);
            return true;
        }catch (Exception e) {
            return false;
        }
        
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return products.searchProducts(keyword);
    }

    @Override
    public boolean createTransaction(User buyer, User seller, double amount) {
        return transactions.processTransaction(buyer, seller, amount);
    }

    @Override
    public boolean sendMessage(String senderId, String receiverId, String content, String productID) {
        //String senderId, String receiverId, String messageContent, String productId
        try {
            messages.sendMessage(senderId, receiverId, content, productID);
            return true;
        } catch (Exception e) {
            return false;
        }
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
                        out.println(registerUser(args[0], args[1], args[2], args[3]));
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
                        String productID = args[4];
                        String category = args[5];
                        out.println(addProduct(productID, name, description, price, category));
                        //String productID, String name, String description, double price, String category
                        break;
                    }
                    case "SEARCH":
                        for (Product p : searchProducts(tokens[1])) {
                            out.println(p.toString());
                        }
                        break;
                    case "TRANSACT": {
                        String[] args = tokens[1].split(" ");
                        out.println(createTransaction((User) UserService.getUserById((String) inputLine),(User) UserService.getUserById((String) args[3]), (double) Double.parseDouble(args[1]) ));
                        break;
                    }
                    case "MESSAGE": {
                        String[] args = tokens[1].split(" ", 3);
                        int senderId = Integer.parseInt(args[0]);
                        int receiverId = Integer.parseInt(args[1]);
                        String content = args[2];
                        out.println(sendMessage(String.valueOf(senderId), String.valueOf(receiverId), content, args[4]));

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
