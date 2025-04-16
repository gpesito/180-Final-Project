import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * The ClientHandler class processes client requests from the server
 * It reads input from the client, executes commands, and sends back responses
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 *
 * @author Milica Slavkovic
 * @version April 6, 2025
 */

public class ClientHandler implements Runnable {
    private final Socket socket;
    private final IProductService productService;

    public ClientHandler(Socket socket, IProductService productService) {
        this.socket = socket;
        this.productService = productService;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String input;
            while ((input = in.readLine()) != null) {
                String[] parts = input.split("::");
                String command = parts[0].toUpperCase();

                switch (command) {
                    case "ADD":
                        productService.addProduct(parts[1], parts[2], parts[3], Double.parseDouble(parts[4]), parts[5]);
                        out.println("Product added.");
                        break;
                    case "DELETE":
                        productService.deleteProduct(parts[1]);
                        out.println("Product deleted.");
                        break;
                    case "SEARCH_CATEGORY":
                        List<IProduct> byCat = productService.searchByCategory(parts[1]);
                        for (IProduct p : byCat) out.println(p);
                        break;
                    case "SEARCH_KEYWORD":
                        List<IProduct> byKey = productService.searchProducts(parts[1]);
                        for (IProduct p : byKey) out.println(p);
                        break;
                    case "EXIT":
                        out.println("Goodbye!");
                        return;
                    default:
                        out.println("Unknown command.");
                }
            }
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
