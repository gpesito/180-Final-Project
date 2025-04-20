import java.io.*;
import java.net.Socket;

/**
 * The Client class is responsible for interacting with the server. It sends commands to the server 
 * and receives responses. It acts as the client-side application in a client-server architecture.
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 * @author 
 * @version April 16, 2025
 */
public class Client {

    private static final String SERVER_ADDRESS = "localhost";  // Server address
    private static final int SERVER_PORT = 12345;  // Server port

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connected to the server.");
            String userCommand;
            
            // Continuously read user input and send it to the server
            while (true) {
                System.out.println("Enter a command (ADD, DELETE, SEARCH_CATEGORY, SEARCH_KEYWORD, EXIT): ");
                userCommand = userInput.readLine();
                
                if (userCommand != null) {
                    out.println(userCommand);  // Send the command to the server

                    if (userCommand.equalsIgnoreCase("EXIT")) {
                        System.out.println("Exiting...");
                        break;  // Exit the loop and terminate the client
                    }

                    // Read and print the server's response
                    String serverResponse;
                    while ((serverResponse = in.readLine()) != null) {
                        System.out.println(serverResponse);
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
    }
}
