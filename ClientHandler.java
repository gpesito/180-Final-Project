import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * The ClientHandler class processes client requests from the server.
 * It reads input from the client, executes commands, and sends back responses.
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 *
 * @author Milica Slavkovic
 * @version April 25, 2025
 */

public class ClientHandler implements Runnable {
    private final Socket socket;
    private final IProductService productService;
    
    // Constructor takes a socket and a reference to the shared product service
    public ClientHandler(Socket socket, IProductService productService) {
        this.socket = socket;
        this.productService = productService;
    }

    // Handles all communication with a single client
    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String input;

            // Listen for client commands
            while ((input = in.readLine()) != null) {
                String[] parts = input.split("::");
                String command = parts[0].toUpperCase();

                try {
                    switch (command) {
                        case "ADD":
                            if (parts.length < 6) {
                                out.println("Error: Invalid ADD command. Usage: ADD::id::name::description::price::category");
                                out.println();
                                break;
                            }
                            productService.addProduct(parts[1], parts[2], parts[3], Double.parseDouble(parts[4]), parts[5]);
                            out.println("Product added.");
                            out.println();
                            break;

                        case "DELETE":
                            if (parts.length < 2) {
                                out.println("Error: Invalid DELETE command. Usage: DELETE::id");
                                out.println();
                                break;
                            }
                            productService.deleteProduct(parts[1]);
                            out.println("Product deleted.");
                            out.println();
                            break;

                        case "SEARCH_CATEGORY":
                            if (parts.length < 2) {
                                out.println("Error: Invalid SEARCH_CATEGORY command. Usage: SEARCH_CATEGORY::category");
                                out.println();
                                break;
                            }
                            List<IProduct> byCat = productService.searchByCategory(parts[1]);
                            for (IProduct p : byCat) out.println(p);
                            out.println();
                            break;

                        case "SEARCH_KEYWORD":
                            if (parts.length < 2) {
                                out.println("Error: Invalid SEARCH_KEYWORD command. Usage: SEARCH_KEYWORD::keyword");
                                out.println();
                                break;
                            }
                            List<IProduct> byKey = productService.searchProducts(parts[1]);
                            for (IProduct p : byKey) out.println(p);
                            out.println();
                            break;
                            
                        case "EXIT":
                            // Exit the loop and close connection
                            out.println("Goodbye!");
                            out.println();
                            return;

                        default:
                            out.println("Unknown command.");
                            out.println();
                            break;
                    }
                } catch (Exception e) {
                    out.println("Error: " + e.getMessage());
                    out.println();
                }
            }
        } catch (IOException e) {
            // Handle disconnection or read/write issues
            System.err.println("Client disconnected or error occurred: " + e.getMessage());
        }
    }
}
