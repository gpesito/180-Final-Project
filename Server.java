import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The Server class listens for client connections and manages communication
 * between the clients and the product service. It uses multithreading to 
 * handle multiple clients concurrently
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 *
 * @author Milica Slavkovic
 * @version April 6, 2025
 */

public class Server implements Runnable, IServer {
    private final int port;
    private final IProductService productService;

    public Server(int port, IProductService productService) {
        this.port = port;
        this.productService = productService;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket, productService)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        new Thread(this).start();
    }
}
